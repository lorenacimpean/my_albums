package com.example.myalbums.ui.profile_screen

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityContactDetailsBinding
import com.example.myalbums.di.DisposableActivity

class ContactDetailsActivity : DisposableActivity() {

    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_details)
        setSupportActionBar(binding.toolbarLayout.toolbar)

    }
}