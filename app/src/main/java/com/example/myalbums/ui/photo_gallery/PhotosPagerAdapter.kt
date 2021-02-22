package com.example.myalbums.ui.photo_gallery

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myalbums.models.Photo
import kotlin.properties.Delegates

class PhotosPagerAdapter(
        activity: AppCompatActivity) :
        FragmentStateAdapter(activity) {

    var photos: ArrayList<Photo> by Delegates.observable(arrayListOf()) { _, _, _ ->
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(photos[position].url)
    }

}