package com.cdmp.rickmorty

import android.app.Application
import com.cdmp.rickmorty.di.DiModuleBuilder
import com.cdmp.rickmorty.di.DiModuleBuilder.buildModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, DiModuleBuilder.buildModules())
    }
}