package com.cdmp.rickmorty.di

object DiModuleBuilder {
    fun buildModules() = listOf(
        DataModules.remoteServiceModule,
        DataModules.apiModule,
        DomainModules.useCaseModules,
        PresentationModules.homeModule
    )

}


