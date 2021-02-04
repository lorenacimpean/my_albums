package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentHomeBinding
import com.example.myalbums.di.DisposableFragment
import com.example.myalbums.models.Album
import com.example.myalbums.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : DisposableFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()
    private var albums: List<Album> = listOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToAlbumsList()
        viewModel.input.onStart.onNext(true)

    }

    private fun listenToAlbumsList() {
        disposeLater(viewModel.output.albums.subscribe {
            when (it.status) {
                Status.SUCCESS -> albums = it.data!!
                Status.LOADING -> print("ADD HANDLE LOADING")
                Status.ERROR -> print("ADD HANDLE ERROR")

            }

        })
    }

}