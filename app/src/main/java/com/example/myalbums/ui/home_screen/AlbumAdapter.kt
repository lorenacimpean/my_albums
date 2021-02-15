package com.example.myalbums.ui.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.AlbumCellBinding
import com.example.myalbums.models.Album
import kotlin.properties.Delegates

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder?>() {

    var albumsList: List<Album> by Delegates.observable(listOf()) { _, old, new ->
    }

    class ViewHolder(val albumCellBinding: AlbumCellBinding) : RecyclerView.ViewHolder(albumCellBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<AlbumCellBinding>(LayoutInflater.from(parent.context), R.layout
            .album_cell, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.albumCellBinding.album = albumsList[position]
    }

    override fun getItemCount() = albumsList.size

    fun addAlbums(list: List<Album>) {
        albumsList = list
        notifyDataSetChanged()
    }

}