package com.example.myalbums.ui.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutDetailsHeaderBinding
import com.example.myalbums.databinding.LayoutPhotoCellBinding
import com.example.myalbums.ui.album_details.AlbumDetailsItem
import com.jakewharton.rxbinding.view.RxView
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.properties.Delegates

class AlbumDetailsAdapter : RecyclerView.Adapter<AlbumDetailsAdapter.AlbumDetailsViewHolder>() {

    val clickSubject: PublishSubject<AlbumDetailsItem> = PublishSubject.create()
    var itemList: List<AlbumDetailsItem> by Delegates.observable(listOf()) { _, _, _ ->
    }

    inner class AlbumDetailsViewHolder : RecyclerView.ViewHolder {

        var headerBinding: LayoutDetailsHeaderBinding? = null
        var photoCellBinding: LayoutPhotoCellBinding? = null

        constructor(binding: LayoutDetailsHeaderBinding) : super(binding.root) {
            headerBinding = binding
        }

        constructor(binding: LayoutPhotoCellBinding) : super(binding.root) {
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
            0 -> holder.headerBinding?.let { binding ->
                binding.header = itemList[position].header
                RxView.clicks(binding.root)
                    .subscribe {
                        clickSubject.onNext(
                            itemList[position]
                        )
                    }
            }
            else -> holder.photoCellBinding?.let { binding ->
                binding.photo = itemList[position].photo
                RxView.clicks(binding.root)
                    .subscribe {
                        clickSubject.onNext(
                            itemList[position]
                        )
                    }
            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position].header != null) {
            0
        }
        else {
            1
        }
    }
}

enum class ItemType {
    HEADER,
    PHOTO
}
