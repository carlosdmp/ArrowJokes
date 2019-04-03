package com.cdmp.rickmorty.presentation.home

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface Interactor {
    val CoroutineContext: CoroutineContext
}

object UiInteractor : Interactor {
    override val CoroutineContext: CoroutineContext
        get() = Dispatchers.Main

}