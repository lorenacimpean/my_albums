package com.example.myalbums.ui.home_screen

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutRecyclerViewBinding
import com.example.myalbums.models.Album

class AlbumsRecyclerView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var binding: LayoutRecyclerViewBinding
    private var adapter: AlbumAdapter

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_recycler_view, this, true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AlbumAdapter()
        binding.recyclerView.adapter = adapter
    }

    fun addAlbums(list: List<Album>) {
        adapter.list = list
        adapter.notifyDataSetChanged()
    }

}