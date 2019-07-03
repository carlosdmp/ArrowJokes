package com.cdmp.rickmorty

import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.cdmp.rickmorty.presentation.home.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.cdmp.arrowjokes", appContext.packageName)
    }

    @Rule
    @JvmField
    public val rule = ActivityTestRule(MainActivity::class.java)

    private val username_tobe_typed = "Ajesh"
    private val correct_password = "password"
    private val wrong_password = "passme123"

    @Test
    fun login_success() {
    }

    @Test
    fun login_failure() {

    }
}
