package com.example.myalbums.ui.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutAlbumCellBinding
import com.example.myalbums.models.Album
import kotlin.properties.Delegates

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder?>() {

    var onItemClick: ((Album) -> Unit)? = null
    var albumsList: List<Album> by Delegates.observable(listOf()) { _, old, new ->
    }

    inner class ViewHolder(val albumCellBinding: LayoutAlbumCellBinding) : RecyclerView.ViewHolder(albumCellBinding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(albumsList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutAlbumCellBinding>(LayoutInflater.from(parent.context), R.layout
            .layout_album_cell, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.albumCellBinding.album = albumsList[position]

    }

    override fun getItemCount() = albumsList.size

}

