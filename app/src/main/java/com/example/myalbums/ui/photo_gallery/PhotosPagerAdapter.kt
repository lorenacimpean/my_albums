package com.example.myalbums.ui.photo_gallery

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myalbums.models.Photo

class PhotosPagerAdapter(activity: AppCompatActivity, private val photos: ArrayList<Photo>) :
        FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(photos[position].url)
    }

}