package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentHomeBinding
import com.example.myalbums.di.DisposableFragment
import com.example.myalbums.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : DisposableFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        listenToAlbumsList()
        viewModel.input.onFragmentStart.onNext(true)
    }

    private fun listenToAlbumsList() {
        disposeLater(viewModel.output.albumsFetched.subscribe {
            when (it.state) {
                State.SUCCESS -> print("ADD HANDLE DATA TO RECYCLER VIEW")
                State.LOADING -> print("ADD HANDLE LOADING")
                State.ERROR -> print("ADD HANDLE ERROR")
            }
        })
    }
}