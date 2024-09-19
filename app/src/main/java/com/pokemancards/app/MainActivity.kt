package com.pokemancards.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.pokemancards.app.pages.detail.PokemanCardDetailFragment
import com.pokemancards.app.pages.list.PokemanCardsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("ASKAR", "onCreate: ")

        val navHostFragment = NavHostFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, navHostFragment)
            .commitNow()

        navController = navHostFragment.navController
        navController.setGraph(createNavGraph(), null)

    }

    private fun createNavGraph(): NavGraph {
        // Create a NavGraph
        return navController.createGraph(startDestination = "list") {
            fragment<PokemanCardsListFragment>("list") { PokemanCardsListFragment() }
            fragment<PokemanCardDetailFragment>("detail") {
                PokemanCardDetailFragment()
            }
        }
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }
}
