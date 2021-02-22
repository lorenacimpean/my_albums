package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityPhotoGalleryBinding

import com.example.myalbums.models.Photo

const val PHOTOS = "photos"

class PhotoGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoGalleryBinding
    private lateinit var pagerAdapter: PhotosPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_gallery)
        intent.getParcelableArrayListExtra<Photo>(PHOTOS)
            ?.let {
                pagerAdapter = PhotosPagerAdapter(this)
                pagerAdapter.photos = it
                binding.viewPager.adapter = pagerAdapter
                pagerAdapter.notifyDataSetChanged()
            }

    }
}