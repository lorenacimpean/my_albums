package com.example.myalbums.ui.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutAlbumCellBinding
import com.example.myalbums.models.Album
import com.jakewharton.rxbinding.view.RxView
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.properties.Delegates

class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder?>() {

    val clickSubject: PublishSubject<Album> = PublishSubject.create()
    var albumsList: List<Album> by Delegates.observable(listOf()) { _, _, _ ->
    }

    inner class ViewHolder(val albumCellBinding: LayoutAlbumCellBinding) : RecyclerView.ViewHolder(albumCellBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutAlbumCellBinding>(LayoutInflater.from(parent.context), R.layout
            .layout_album_cell, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.albumCellBinding.album = albumsList[position]
        RxView.clicks(viewHolder.albumCellBinding.root)
            .subscribe {
                clickSubject.onNext(
                    albumsList[position]
                )
            }

    }

    override fun getItemCount() = albumsList.size

}

