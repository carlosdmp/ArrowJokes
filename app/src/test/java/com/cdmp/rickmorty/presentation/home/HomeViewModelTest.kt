package com.cdmp.rickmorty.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cdmp.rickmorty.CoroutinesMainDispatcherRule
import com.cdmp.rickmorty.domain.model.CharacterListModel
import com.cdmp.rickmorty.domain.model.CharacterModel
import com.cdmp.rickmorty.domain.usecase.GetCharacterCase
import com.cdmp.rickmorty.presentation.home.model.CharacterDisplayModel
import com.cdmp.rickmorty.presentation.home.model.HomeItemDisplayModel
import com.cdmp.rickmorty.presentation.home.model.LoadingDisplayModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
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

    val mockedCase = mockk<GetCharacterCase>()
    val mockedModel = CharacterListModel(
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
    val mockedDisplay = listOf(
        CharacterDisplayModel(
            name = "testName",
            image = "testImage",
            id = 1
        ),
        LoadingDisplayModel
    )
    val observer = mockk<Observer<List<HomeItemDisplayModel>>>().apply {
        every { onChanged(any()) } returns Unit
    }

    lateinit var vm: HomeViewModel

    @Before
    fun setUp() {
        vm = HomeViewModel(
            getCharacterCase = mockedCase,
            scope = testScope()
        ).apply {
            characterList.observeForever(observer)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun initialLiveData() {
        vm
        verify { observer.onChanged(listOf<CharacterDisplayModel>()) }
        assertEquals(vm.characterList.value, listOf<CharacterDisplayModel>())
    }

    @Test
    fun getCharacterListWithNextPage() {
        every { mockedCase.getPage(1) } returns mockedModel
        vm.start()
        verify { mockedCase.getPage(1) }
        verifyOrder {
            observer.onChanged(listOf<CharacterDisplayModel>())
            observer.onChanged(mockedDisplay)
        }
        assertEquals(vm.characterList.value, mockedDisplay)
    }

    @Test
    fun start() {
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
        verify { observer.onChanged(listOf<CharacterDisplayModel>()) }
        assertEquals(vm.characterList.value, listOf<CharacterDisplayModel>())
    }

}

