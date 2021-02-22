package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentPhotoBinding
import com.example.myalbums.di.BaseFragment
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : BaseFragment() {

    private lateinit var binding: FragmentPhotoBinding
    private val viewModel: PhotoViewModel by viewModel<PhotoViewModel>()

    override fun onStart() {
        super.onStart()
        disposeLater(viewModel.output.onBackClicked.subscribeOnMainThread { _ ->

            activity?.supportFragmentManager?.popBackStack()
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        binding.url = requireArguments().getString(PHOTO_URL)
        binding.onBackClick = viewModel.input.clickOnBack
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