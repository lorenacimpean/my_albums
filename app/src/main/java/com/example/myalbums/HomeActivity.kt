package com.example.myalbums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.myalbums.databinding.ActivityHomeBinding
import com.onesignal.OneSignal

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(BuildConfig.ONE_SIGNAL_KEY)
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