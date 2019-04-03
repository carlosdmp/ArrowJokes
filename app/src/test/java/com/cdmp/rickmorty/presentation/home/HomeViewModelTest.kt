package com.cdmp.rickmorty.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.cdmp.rickmorty.domain.usecase.GetCharacterCase
import com.cdmp.rickmorty.presentation.home.model.HomeItemDisplayModel
import com.cdmp.rickmorty.utils.default
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import kotlin.math.exp

class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val mockedCase = mockk<GetCharacterCase>()
    val vm = HomeViewModel(getCharacterCase = mockedCase)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun initialLiveData() {
        val mutableLiveData = MutableLiveData<String>()

        mutableLiveData.postValue("test")

        assertEquals("test", mutableLiveData.value)
    }

    @Test
    fun getCharacterList() {
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
    }
}

