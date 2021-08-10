package com.example.restaurantsearch

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var tvSearch: TextView
    lateinit var tvHeader: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        tvHeader = findViewById(R.id.tvHeader)
        tvSearch = findViewById(R.id.tvSearch)
        tvSearch.setOnClickListener {
            navController.navigate(R.id.searchFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)

    }

    override fun onPause() {
        navController.removeOnDestinationChangedListener(listener)
        super.onPause()
    }


    private val listener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if (navController.currentDestination?.id == R.id.splashFragment
                || navController.currentDestination?.id == R.id.searchFragment
            ) {
                tvSearch.visibility = View.GONE
                tvHeader.visibility = View.GONE
            } else if (navController.currentDestination?.id == R.id.menuFragment) {
                tvSearch.visibility = View.GONE
                tvHeader.visibility = View.VISIBLE
                tvHeader.text = "Menu Items"
            } else {
                tvSearch.visibility = View.VISIBLE
                tvHeader.visibility = View.VISIBLE
                tvHeader.text = "Restaurants"
            }
        }

}