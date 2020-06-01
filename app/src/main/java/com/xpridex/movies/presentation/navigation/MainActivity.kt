package com.xpridex.movies.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.xpridex.movies.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
        setupActionBar()
        destinationManager()
    }

    private fun setupNavController() {
        navController = Navigation.findNavController(
            this,
            R.id.navHostFragment
        )
    }

    private fun setupActionBar() {
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.movieListFragment
            )
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun destinationManager() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.splashFragment -> {
                    supportActionBar?.hide()
                }

                else -> {
                    supportActionBar?.show()

                }
            }
        }
    }
}