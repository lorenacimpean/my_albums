package com.example.myalbums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myalbums.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navigationController = findNavController(R.id.navigationHostFragment)
        binding.bottomNavView.setupWithNavController(navigationController)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.friendsFragment,
                R.id.newsFragment,
                R.id.profileFragment
            )
        )
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setupActionBarWithNavController(navigationController, appBarConfiguration)
    }


}