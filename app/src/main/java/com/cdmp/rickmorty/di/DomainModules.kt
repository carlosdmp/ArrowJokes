package com.cdmp.rickmorty.di

import com.cdmp.rickmorty.domain.usecase.GetCharacterCase
import org.koin.dsl.module.module

object DomainModules {
    val useCaseModules = module {
        single { GetCharacterCase(get()) }
    }
}