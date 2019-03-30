package com.cdmp.rickmorty.presentation.home

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.effects.extensions.io.fx.fxCancellable
import arrow.effects.extensions.io.unsafeRun.runBlocking
import androidx.lifecycle.viewModelScope
import arrow.core.Try
import arrow.unsafe
import com.cdmp.rickmorty.data.entity.CharacterList
import com.cdmp.rickmorty.domain.usecase.GetAllCharactersCase
import com.cdmp.rickmorty.utils.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getAllCharactersCase: GetAllCharactersCase) : ViewModel() {

    val characterList = MutableLiveData<CharacterList?>().default(null)
    private val disposer = mutableListOf<() -> Unit>()

    fun startArrowVersion() {
        val (fetchCharacters, cancel) = fxCancellable {
            val result = !effect { getAllCharactersCase() }.attempt()
            Dispatchers.Main.shift()
            result.fold(
                { !effect { it.reportError() } },
                { !effect { it.displayResult() } }
            )
        }

        disposer.add(cancel)
        unsafe { runBlocking { fetchCharacters } }
    }

    fun startKtxVersion() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Try { getAllCharactersCase() }
            }.fold({
                it.reportError()
            }, {
                it.displayResult()
            })

        }
    }

    @UiThread
    private suspend fun Throwable.reportError(): Unit =
        println(message)

    @UiThread
    private suspend fun CharacterList.displayResult() {
        characterList.postValue(this)
    }

}


