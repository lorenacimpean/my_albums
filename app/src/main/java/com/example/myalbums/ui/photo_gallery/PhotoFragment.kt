package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentPhotoBinding
import com.example.myalbums.di.BaseFragment

class PhotoFragment : BaseFragment() {

    private lateinit var binding: FragmentPhotoBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        binding.url = requireArguments().getString(PHOTO_URL)
        return binding.root
    }

    companion object {

        private const val PHOTO_URL = "photoUrl"
        fun newInstance(photoUrl: String): PhotoFragment {
            val fragment = PhotoFragment()
            val args = Bundle()
            args.putString(PHOTO_URL, photoUrl)
            fragment.arguments = args
            return fragment
        }
    }
}