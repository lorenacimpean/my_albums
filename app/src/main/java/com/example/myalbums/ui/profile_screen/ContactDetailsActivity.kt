package com.example.myalbums.ui.profile_screen

import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityContactDetailsBinding
import com.example.myalbums.di.DisposableActivity

class ContactDetailsActivity : DisposableActivity() {

    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_details)
        binding.toolbarLayout.toolbar.title = getString(R.string.contact_info)
        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        menu.getItem(0).title = getText(R.string.apply)
        menu.getItem(0).icon = null
        return super.onCreateOptionsMenu(menu)
    }

}