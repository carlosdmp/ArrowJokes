package com.cdmp.rickmorty.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.cdmp.rickmorty.R

class MainActivity : AppCompatActivity() {

    interface MainActivityHosted {
        var navigationController: MainActivityNavigationController?
    }

    interface MainActivityNavigationController {
        fun navigateToCharacters()
        fun navigateToLocations()
    }

    private val navigationController: MainActivityNavigationController = object : MainActivityNavigationController {
        override fun navigateToCharacters() {
            navigateTo(HomeFragment.newInstance(this))
        }

        override fun navigateToLocations() {
            navigateTo(HomeFragment.newInstance(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            navigateTo(HomeFragment.newInstance(navigationController))
        }
    }

    private fun <T> navigateTo(fragment: T) where T : Fragment, T : MainActivityHosted {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

}
