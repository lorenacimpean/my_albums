package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityPhotoGalleryBinding
import com.example.myalbums.models.Photo

class PhotoGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoGalleryBinding
    private lateinit var photos: List<Photo>
    private lateinit var pagerAdapter: PhotosPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_gallery)
        pagerAdapter = PhotosPagerAdapter(supportFragmentManager, photos)
        binding.viewPager.adapter = pagerAdapter

    }
}