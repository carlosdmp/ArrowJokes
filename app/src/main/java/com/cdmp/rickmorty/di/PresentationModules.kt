package com.cdmp.rickmorty.di

import com.cdmp.rickmorty.presentation.home.HomeViewModel
import com.cdmp.rickmorty.presentation.home.viewModelScope
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object PresentationModules {
    val homeModule = module {
        viewModel { HomeViewModel(getCharacterCase = get(), scope = viewModelScope()) }
    }
}

