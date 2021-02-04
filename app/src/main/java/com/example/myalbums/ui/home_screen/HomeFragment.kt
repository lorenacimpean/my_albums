package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myalbums.R
import com.example.myalbums.di.DisposableFragment
import com.example.myalbums.models.Album
import com.example.myalbums.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : DisposableFragment() {

    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()
    private lateinit var albums: List<Album>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposeLater(viewModel.output.albums.subscribe {
            when (it.status) {
                Status.SUCCESS -> albums = it?.data!!
                Status.LOADING -> print("ADD HANDLE LOADING")
                Status.ERROR -> print("ADD HANDLE ERROR")

            }

        })
        viewModel.input.onStart.onNext(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}