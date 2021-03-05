package com.example.myalbums.ui.friends_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutFriendCellBinding
import com.example.myalbums.models.Friend
import kotlin.properties.Delegates

class FriendsAdapter() : RecyclerView.Adapter<FriendsAdapter.ViewHolder?>() {

    var friendsList : List<Friend> by Delegates.observable(listOf()) { _, _, _ ->
    }

    inner class ViewHolder(val friendsBinding : LayoutFriendCellBinding) :
            RecyclerView.ViewHolder(friendsBinding.root)

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutFriendCellBinding>(
                LayoutInflater.from(parent.context), R.layout
                .layout_friend_cell, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder : ViewHolder, position : Int) {
        viewHolder.friendsBinding.friend = friendsList[position]
    }

    override fun getItemCount() = friendsList.size
}
