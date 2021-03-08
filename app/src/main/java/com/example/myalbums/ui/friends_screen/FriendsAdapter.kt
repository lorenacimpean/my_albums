package com.example.myalbums.ui.friends_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbums.databinding.LayoutFriendCellBinding
import com.example.myalbums.models.Friend
import kotlin.properties.Delegates

class FriendsAdapter(private val clickListener : LiveDataClickListener) : RecyclerView.Adapter<ViewHolder?>() {

    var friendsList : List<Friend> by Delegates.observable(listOf()) { _, _, _ ->
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        return ViewHolder.from(
                parent
        )
    }

    override fun onBindViewHolder(viewHolder : ViewHolder, position : Int) {
        viewHolder.bind(friendsList[position], clickListener)
    }

    override fun getItemCount() = friendsList?.size
}

class ViewHolder(private val friendsBinding : LayoutFriendCellBinding) :
        RecyclerView.ViewHolder(friendsBinding.root) {

    companion object {

        fun from(parent : ViewGroup) : ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutFriendCellBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(
                    binding
            )
        }
    }

    fun bind(
        item : Friend,
        clickListener : LiveDataClickListener
    ) {
        friendsBinding.friend = item
        friendsBinding.listener = clickListener
        friendsBinding.executePendingBindings()
    }
}

class LiveDataClickListener(
    val clickListener : (friendId : Int) -> Unit) {
    fun onClick(friend : Friend) = clickListener(friend.id)
}


