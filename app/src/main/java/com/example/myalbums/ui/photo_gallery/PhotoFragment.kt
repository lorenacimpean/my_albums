package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentPhotoBinding

class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)

        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.url = requireArguments().getString(PHOTO_URL)

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