package com.example.myalbums.ui.album_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutDetailsHeaderBinding
import com.example.myalbums.databinding.LayoutPhotoCellBinding
import com.example.myalbums.ui.album_details.AlbumDetailsItem.Companion.HEADER
import com.example.myalbums.utils.RxOnItemClickListener
import kotlin.properties.Delegates

class AlbumDetailsAdapter(private val onItemClickListener: RxOnItemClickListener<AlbumDetailsItem>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList: List<AlbumDetailsItem> by Delegates.observable(listOf()) { _, _, _ ->
    }

    inner class HeaderViewHolder(binding: LayoutDetailsHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

        var headerBinding: LayoutDetailsHeaderBinding? = binding

    }

    inner class PhotoViewHolder(binding: LayoutPhotoCellBinding) : RecyclerView.ViewHolder(binding.root) {

        var photoCellBinding: LayoutPhotoCellBinding? = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding
        when (viewType) {
            HEADER -> {
                binding = DataBindingUtil.inflate<LayoutDetailsHeaderBinding>(inflater,
                                                                              R.layout.layout_details_header,
                                                                              parent,
                                                                              false)
                return HeaderViewHolder(binding)
            }

            else   -> {

                binding =
                    DataBindingUtil.inflate<LayoutPhotoCellBinding>(inflater,
                                                                    R.layout.layout_photo_cell,
                                                                    parent,
                                                                    false)
                return PhotoViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = itemList[position]
        if (element.type == HEADER) {
            val binding = (holder as HeaderViewHolder).headerBinding
            binding?.header = element.header
        }
        else {
            val binding = (holder as PhotoViewHolder).photoCellBinding
            binding?.let {
                it.item = element
                it.listener = onItemClickListener
            }

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].type
    }

}


