package com.cdmp.rickmorty

import arrow.core.*
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.internal.matchers.text.ValuePrinter

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExamplesEither {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun exists() {
        val stringOrInt: Either<String, Int> = Either.Right(5)

        stringOrInt.fold({
            println(it)
        }, {
            println(it)
        })

        //change to left
    }

    @Test
    fun canFail() {
        val value = "3"

        val parsed: Either<NumberFormatException, Int> =
            if (value.matches(Regex("-?[0-9]+"))) Either.Right(value.toInt())
            else Either.Left(NumberFormatException("$value is not a valid integer."))

        val hasError = parsed.isLeft()
        val hasNumber = parsed.isRight()
        val hasThreeCorrect = parsed.contains(6)

        parsed.fold({
            println("Killo, la has liado")
        }, {
            println(it)
        })
    }

    @Test
    fun tryExample() {
        val value = Try { "3".toInt() }

        value.toEither {  }
        value.fold({
            println("Killo, la has liado" )
        }, {
            println(it)
        })
    }

    @Test
    fun tryExampleDefault() {
        val value = Try { null!!.toInt() }

        println(value.getOrDefault { 0 })

        println(value.getOrElse { error -> if (error is java.lang.NumberFormatException) 0 else 1 })

        //example of mapings between types
        value.toEither()
        value.toOption()
        value.toEither().toOption()
    }
}