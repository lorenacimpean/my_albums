package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentPhotoBinding
import com.example.myalbums.models.Photo

private const val PHOTO_URL = "url"

class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        val args = arguments
        binding.url = args?.getString(PHOTO_URL)
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    companion object {

        fun newInstance(photo: Photo): PhotoFragment {
            val args = Bundle()
            args.putString(PHOTO_URL, photo.url)
            val fragment = PhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}