package com.example.myalbums


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myalbums.custom_views.CustomToolbar
import com.example.myalbums.custom_views.IconType
import com.example.myalbums.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toolbar: CustomToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        val navigationController = findNavController(R.id.navigationHostFragment)
        toolbar = binding.toolbarLayout.toolbar
        binding.bottomNavView.setupWithNavController(navigationController)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.friendsFragment,
                R.id.newsFragment,
                R.id.profileFragment
            )
        )

        toolbar.configureToolbar("TestTile", IconType.BACK, IconType.APPLY)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navigationController, appBarConfiguration)

    }

}