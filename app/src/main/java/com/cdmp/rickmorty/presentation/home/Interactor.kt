package com.cdmp.rickmorty.presentation.home

import arrow.core.Try
import arrow.effects.extensions.io.fx.fxCancellable
import arrow.effects.fix
import com.cdmp.rickmorty.data.entity.CharacterList
import reactor.util.function.Tuple2

interface Interactor {
  //  fun <T> execute(f: (() -> Try<T>)): Tuple2<Try<T>, () -> Unit>
}

object FxInteractor : Interactor {
//    override fun <T> execute(f: (() -> Try<T>)): Tuple2<Try<T>, () -> Unit> {
//        val (resultKind, disposable) = fxCancellable {
//            f.invoke()
//        }
//        val result: Try<T> = resultKind.fix().unsafeRunSync()
//        return Tuple2(result, disposable)
//    }
}