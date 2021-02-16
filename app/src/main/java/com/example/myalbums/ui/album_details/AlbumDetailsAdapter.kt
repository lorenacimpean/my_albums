package com.example.myalbums.ui.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutDetailsHeaderBinding
import com.example.myalbums.databinding.LayoutPhotoCellBinding
import com.example.myalbums.models.AlbumDetailsItem
import kotlin.properties.Delegates

class AlbumDetailsAdapter() : RecyclerView.Adapter<AlbumDetailsAdapter.AlbumDetailsViewHolder>() {

    var onItemClick: ((AlbumDetailsItem) -> Unit)? = null
    var itemAlbumDetails: List<AlbumDetailsItem> by Delegates.observable(listOf()) { _, _, _ ->
    }

    inner class AlbumDetailsViewHolder : RecyclerView.ViewHolder {

        var headerBinding: LayoutDetailsHeaderBinding? = null
        var photoCellBinding: LayoutPhotoCellBinding? = null

        constructor(binding: LayoutDetailsHeaderBinding) : super(binding.getRoot()) {
            headerBinding = binding
        }

        constructor(binding: LayoutPhotoCellBinding) : super(binding.getRoot()) {
            photoCellBinding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding
        when (viewType) {
            ItemType.HEADER.ordinal -> {
                binding = DataBindingUtil.inflate<LayoutDetailsHeaderBinding>(inflater,
                                                                              R.layout.layout_details_header,
                                                                              parent,
                                                                              false)
                return AlbumDetailsViewHolder(binding)
            }

            else                    -> {

                binding =
                    DataBindingUtil.inflate<LayoutPhotoCellBinding>(inflater, R.layout.layout_photo_cell, parent, false)
                return AlbumDetailsViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: AlbumDetailsViewHolder, position: Int) {
        when (position) {
            0 -> holder.headerBinding?.album = itemAlbumDetails[position].header
            else -> holder.photoCellBinding?.photo = itemAlbumDetails[position].photo
        }
    }

    override fun getItemCount(): Int {
        return itemAlbumDetails.size
    }

}

enum class ItemType {
    HEADER,
    PHOTO
}
