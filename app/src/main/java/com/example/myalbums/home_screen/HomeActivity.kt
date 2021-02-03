package com.example.myalbums.home_screen


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myalbums.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        navigationController = findNavController(R.id.navigationHostFragment)
        bottomNavigationView = findViewById(R.id.bottomNavView)
        bottomNavigationView.setupWithNavController(navigationController)

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.friendsFragment,
                R.id.newsFragment,
                R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navigationController, appBarConfiguration)
    }

}