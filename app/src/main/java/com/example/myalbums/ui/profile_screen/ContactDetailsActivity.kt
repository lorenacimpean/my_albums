package com.example.myalbums.ui.profile_screen

import android.os.Bundle
import com.example.myalbums.R
import com.example.myalbums.di.DisposableActivity

class ContactDetailsActivity : DisposableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
    }
}