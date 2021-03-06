package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentHomeBinding
import com.example.myalbums.di.DisposableFragment
import com.example.myalbums.utils.State
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : DisposableFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listAdapter: AlbumListAdapter
    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.albumsRecyclerView.layoutManager = LinearLayoutManager(context)
        listAdapter = AlbumListAdapter(viewModel.input.onAlbumClick)
        binding.albumsRecyclerView.adapter = listAdapter
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        listenToAlbumsList()
        listenToAlbumClick()
        viewModel.input.onLoadData.onNext(true)
    }

    private fun listenToAlbumClick() {
        disposeLater(viewModel.output.albumClicked.subscribeOnMainThread { album ->
            val directions = HomeFragmentDirections.actionHomeFragmentToAlbumDetailsFragment(album)
            findNavController().navigate(directions)
        })
    }

    private fun listenToAlbumsList() {
        disposeLater(viewModel.output.albumsFetched
                         .subscribeOnMainThread { response ->
                             when (response.state) {
                                 State.SUCCESS -> response.data?.let {
                                     listAdapter.albumsList = it
                                     listAdapter.notifyDataSetChanged()
                                 }
                                 State.LOADING -> print("LOADING")
                                 State.ERROR   -> print("ERROR")
                             }
                         })
    }

}