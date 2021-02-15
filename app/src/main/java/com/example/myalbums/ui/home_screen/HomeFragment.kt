package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentHomeBinding
import com.example.myalbums.di.DisposableFragment
import com.example.myalbums.models.Album
import com.example.myalbums.utils.State
import com.example.myalbums.utils.subscribeOnMainThread
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
        disposeLater(viewModel.output.albumsFetched
                         .subscribeOnMainThread { response ->
                             when (response.state) {
                                 State.SUCCESS -> response.data?.let {
                                     createRecyclerView(it)
                                 }
                                 State.LOADING -> print("LOADING")
                                 State.ERROR   -> print("ERROR")
                             }
                         })
    }

    private fun createRecyclerView(list: List<Album>) {
        binding.albumsRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = AlbumAdapter()
        binding.albumsRecyclerView.adapter = adapter
        adapter.albumsList = list
        adapter.notifyDataSetChanged()
    }
}