package com.example.myalbums.ui.photo_gallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myalbums.models.Photo

class PhotosPagerAdapter(fragmentManager: FragmentManager, private val photos: List<Photo>) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return photos.size
    }

    override fun getItem(position: Int): Fragment {
        return PhotoFragment.newInstance(photos[position])
    }

}