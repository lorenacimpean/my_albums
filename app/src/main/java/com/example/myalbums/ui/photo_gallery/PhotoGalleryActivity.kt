package com.example.myalbums.ui.photo_gallery

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityPhotoGalleryBinding

import com.example.myalbums.di.DisposableActivity
import com.example.myalbums.models.Photo
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

const val PHOTOS = "photos"
const val PHOTO_INDEX = "index"

class PhotoGalleryActivity : DisposableActivity() {

    private lateinit var binding: ActivityPhotoGalleryBinding
    private lateinit var pagerAdapter: PhotosPagerAdapter
    private val viewModel: PhotoGalleryViewModel by viewModel<PhotoGalleryViewModel>()

    override fun onStart() {
        super.onStart()
        disposeLater(viewModel.output.onBackClicked.subscribeOnMainThread { _ ->
            this.finish()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_gallery)
        intent.getParcelableArrayListExtra<Photo>(PHOTOS)
            ?.let {
                pagerAdapter = PhotosPagerAdapter(this)
                pagerAdapter.photos = it
                binding.listener = viewModel.input.clickOnBack
                binding.viewPager.adapter = pagerAdapter
                binding.viewPager.currentItem = intent.getIntExtra(PHOTO_INDEX, 0)
                pagerAdapter.notifyDataSetChanged()

            }

    }
}