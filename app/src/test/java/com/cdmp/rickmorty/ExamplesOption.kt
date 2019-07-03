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
class ExamplesOption {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun optionalExampleExists() {
        val x: Option<String> = Some("Hello")

        val e = x.fold({
            "No value"
        }, {
            it
        })
    }

    @Test
    fun optionalExampleNotExists() {
        val x: Option<String> = None

        x.fold({
            println("No value")
        }, {
            println(it)
        })

    }

    @Test
    fun optionalExampleNotExistsButDefault() {
        val x: Option<String> = None

        x.fold({
            println("No value")
        }, {
            println(it)
        })

        assert(x is None)
        when (x) {
            is None -> {

            }
            is Some -> {
                println(x.t)
            }
        }
        //change for method and default
    }

    @Test
    fun optionalExampleExistsMap() {
        val x: Int? = null
        val optionX = x.toOption()

        val y = optionX.getOrElse { 7 }

        optionX.map { it + 10 }.fold({
            println("No value")
        }, {
            println(it)
        })
    }

    @Test
    fun optionalExampleNotExistsMap() {
        val x: Int? = null
        val optionX = x.toOption()



        optionX.map { it + 10 }.fold({
            println("No value")
        }, {
            println(it)
        })
    }

    fun canReturnOrNot(doesReturn: Boolean) = if (doesReturn) Some("Hello value") else None
}