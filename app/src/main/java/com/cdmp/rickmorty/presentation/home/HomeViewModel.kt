package com.cdmp.rickmorty.presentation.home

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Try
import arrow.effects.extensions.io.fx.fxCancellable
import arrow.unsafe
import com.cdmp.rickmorty.domain.model.CharacterListModel
import com.cdmp.rickmorty.domain.usecase.GetCharacterCase
import com.cdmp.rickmorty.presentation.home.model.CharacterDisplayModel
import com.cdmp.rickmorty.presentation.home.model.HomeItemDisplayModel
import com.cdmp.rickmorty.presentation.home.model.LoadingDisplayModel
import com.cdmp.rickmorty.utils.default
import kotlinx.coroutines.*

class HomeViewModel(
    private val getCharacterCase: GetCharacterCase,
    scope: ViewModelScope = viewModelScope()
) : ViewModel(), ViewModelScope by scope {

    val characterList = MutableLiveData<List<HomeItemDisplayModel>>().default(listOf())
    private var nextPage: Int? = 1

    fun start() {
        loadCharacters()
    }

    fun loadNext() {
        loadCharacters()
    }

    private fun loadCharacters() {
        launch {
            withContext(IO) {
                Try { getCharacterCase.getPage(nextPage) }
            }.fold({
                it.reportError()
            }, {
                displayResult(it)
            })
        }
    }

    fun loadCharactersFx() {
        val (fetchCharacters, cancel) = fxCancellable {
            val result = !effect { getCharacterCase.getPage(nextPage) }.attempt()
            Dispatchers.Main.shift()
            result.fold(
                { !effect { it.reportError() } },
                { !effect { displayResult(it) } }
            )
        }
        unsafe { runBlocking { fetchCharacters } }
    }

    @UiThread
    private fun Throwable.reportError(): Unit =
        println(message)

    @UiThread
    private fun displayResult(result: CharacterListModel) {
        nextPage = result.nextPage
        val previousPage = characterList.value?.toMutableList()?.apply {
            removeAll { it is LoadingDisplayModel }
        }
        val nextPage: MutableList<HomeItemDisplayModel> = result.list.map { it.toDisplayModel() }.toMutableList()
        nextPage.apply {
            if (result.nextPage != null) {
                add(LoadingDisplayModel)
            }
        }
        characterList.value = (previousPage?.apply { addAll(nextPage) })
    }

    fun characterClicked(id: Int) {
        val newList = (characterList.value?.toMutableList()?.apply {
            remove(find { it is CharacterDisplayModel && it.id == id })
        })
        characterList.value = newList
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}


