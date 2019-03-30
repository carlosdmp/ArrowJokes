package com.cdmp.rickmorty.di

import com.cdmp.rickmorty.domain.usecase.GetAllCharactersCase
import org.koin.dsl.module.module

object DomainModules {
    val useCaseModules = module {
        single { GetAllCharactersCase(get()) }
    }
}