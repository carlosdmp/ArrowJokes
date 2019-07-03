package com.cdmp.rickmorty.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import arrow.core.None
import arrow.core.getOrElse
import com.cdmp.rickmorty.domain.model.CharacterListModel
import com.cdmp.rickmorty.domain.model.DomainError
import com.cdmp.rickmorty.domain.usecase.GetCharacterCase
import com.cdmp.rickmorty.presentation.ErrorDisplay
import com.cdmp.rickmorty.presentation.home.model.CharacterDisplayModel
import com.cdmp.rickmorty.presentation.home.model.HomeItemDisplayModel
import com.cdmp.rickmorty.presentation.home.model.LoadingDisplayModel
import com.cdmp.rickmorty.utils.default
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCharacterCase: GetCharacterCase,
    scope: ViewModelScope
) : ViewModel(), ViewModelScope by scope {
    //change to default scope

    val characterList =
        MutableLiveData<Either<ErrorDisplay, List<HomeItemDisplayModel>>>().default(Either.right(listOf()))
    private var nextPagePointer: Int? = 1

    fun start() {
        loadCharacters()
    }

    fun loadNext() {
        loadCharacters()
    }

    private fun loadCharacters() {
        launch {
            withContext(IO) {
                getCharacterCase.getPage(nextPagePointer)
            }.let { result ->
                        nextPagePointer = result.fold(
                            { null },
                            { it.nextPage }
                        )
                        showResult(result)
                    }
            }
    }
    //Change to IO all block

    private fun showResult(data: Either<DomainError, CharacterListModel>) {
        characterList.value = data.bimap({ error -> error.toDisplay() },
            { newPage: CharacterListModel ->
                nextPagePointer = newPage.nextPage
                val loadedItems = (characterList.value?.toOption() ?: None).getOrElse { listOf() }
                loadedItems.toMutableList().apply {
                    removeAll { model -> model is LoadingDisplayModel }
                    addAll(newPage.list.map { it.toDisplayModel() })
                    if (newPage.nextPage != null) {
                        add(LoadingDisplayModel)
                    }
                }
            })
    }

    fun characterClicked(id: Int) {
        val newList = (characterList.value?.map {
            it.toMutableList().apply {
                remove(find { model -> model is CharacterDisplayModel && model.id == id })
            }
        })
        characterList.value = newList
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}


