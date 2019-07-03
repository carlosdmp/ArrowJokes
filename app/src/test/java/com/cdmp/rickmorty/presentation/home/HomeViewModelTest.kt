package com.cdmp.rickmorty.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.cdmp.rickmorty.CoroutinesMainDispatcherRule
import com.cdmp.rickmorty.domain.model.CharacterListModel
import com.cdmp.rickmorty.domain.model.CharacterModel
import com.cdmp.rickmorty.domain.usecase.GetCharacterCase
import com.cdmp.rickmorty.presentation.ErrorDisplay
import com.cdmp.rickmorty.presentation.home.model.CharacterDisplayModel
import com.cdmp.rickmorty.presentation.home.model.HomeItemDisplayModel
import com.cdmp.rickmorty.presentation.home.model.LoadingDisplayModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mockedCase = mockk<GetCharacterCase>()

    private val mockedModel = CharacterListModel(
        list = listOf(
            CharacterModel(
                id = 1,
                name = "testName",
                status = " ",
                species = " ",
                type = " ",
                gender = " ",
                origin = CharacterModel.Location("", ""),
                location = CharacterModel.Location("", ""),
                image = "testImage",
                episode = listOf("", ""),
                url = " ",
                created = " "
            )
        ),
        nextPage = 1
    )
    private val mockedDisplay = listOf(
        CharacterDisplayModel(
            name = "testName",
            image = "testImage",
            id = 1
        ),
        LoadingDisplayModel
    )
    private val observer = mockk<Observer<Either<ErrorDisplay, List<HomeItemDisplayModel>>>>()
        .apply {
            every { onChanged(any()) } returns Unit
        }

    private val vm: HomeViewModel by lazy {
        HomeViewModel(
            getCharacterCase = mockedCase,
            scope = testScope()
        ).apply {
            characterList.observeForever(observer)
        }
    }

    @Test
    fun initialLiveData() {
        vm
        verify { observer.onChanged(Right(listOf<CharacterDisplayModel>())) }
        assertEquals(vm.characterList.value, Right(listOf<CharacterDisplayModel>()))
    }

    @Test
    fun start() {
        //change to every
        coEvery { mockedCase.getPage(1) } returns Either.right(mockedModel)

        vm.start()

        coVerify { mockedCase.getPage(1) }
        verifyOrder {
            observer.onChanged(Right(listOf<CharacterDisplayModel>()))
            observer.onChanged(Right(mockedDisplay))
        }
        assertEquals(vm.characterList.value, Right(mockedDisplay))
    }

    @Test
    fun startWithError() {
        coEvery { mockedCase.getPage(1) } returns Either.left(mockk {
            every { toDisplay() } returns mockk {
                every { message } returns "test message"
            }
        })

        vm.start()

        coVerify { mockedCase.getPage(1) }
        verifyOrder {
            observer.onChanged(Right(listOf<CharacterDisplayModel>()))
            observer.onChanged(any())
        }
        assertEquals(Left(ErrorDisplay("test message")), vm.characterList.value)
    }

    @Test
    fun loadNext() {
    }

    @Test
    fun loadCharactersFunctionalVersion() {
    }

    @Test
    fun characterClicked() {
        vm.characterClicked(1)
        verify { observer.onChanged(Right(listOf<CharacterDisplayModel>())) }
        assertEquals(vm.characterList.value, Right(listOf<CharacterDisplayModel>()))
    }

}

