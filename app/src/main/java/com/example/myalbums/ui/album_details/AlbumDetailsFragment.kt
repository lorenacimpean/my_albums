package com.example.myalbums.ui.album_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentAlbumDetailsBinding
import com.example.myalbums.di.BaseFragment
import com.example.myalbums.models.Album
import com.example.myalbums.utils.State
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumDetailsFragment : BaseFragment() {

    private val args: AlbumDetailsFragmentArgs by navArgs()
    private lateinit var album: Album
    private lateinit var binding: FragmentAlbumDetailsBinding
    private lateinit var listAdapter: AlbumDetailsAdapter
    private val viewModel: AlbumDetailsViewModel by viewModel<AlbumDetailsViewModel>()
    override fun onStart() {
        super.onStart()
        disposeLater(viewModel.output.onDataFetched.subscribeOnMainThread { response ->
            when (response.state) {
                State.SUCCESS -> response.data?.let {
                    listAdapter.itemList = it
                    listAdapter.notifyDataSetChanged()
                }
                State.LOADING -> print("LOADING")
                State.ERROR   -> print("ERROR")
            }
        })
        disposeLater(viewModel.output.onPhotoClicked.subscribeOnMainThread { photo ->
            print(photo?.title)
        })
        viewModel.input.loadData.onNext(album)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        args.album?.let {
            album = it
        }
        super.enableBackButton()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album_details, container, false)
        binding.albumDetailsRecyclerView.layoutManager = LinearLayoutManager(context)
        listAdapter = AlbumDetailsAdapter(viewModel.input.clickOnItem)
        binding.albumDetailsRecyclerView.adapter = listAdapter
        return binding.root
    }

}