package com.example.myalbums.ui.friends_screen

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentFriendsBinding
import com.example.myalbums.di.BaseFragment
import com.example.myalbums.models.Friend

class FriendsFragment : BaseFragment() {

    private lateinit var binding : FragmentFriendsBinding
    private lateinit var listAdapter : FriendsAdapter
    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends, container, false)
        binding.friendsRecyclerView.layoutManager = LinearLayoutManager(context)
        listAdapter = FriendsAdapter()
        binding.friendsRecyclerView.adapter = listAdapter
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        listAdapter.friendsList = listOf(
                Friend(1, "Jane Doe", "jane.doe", "jane.doe@test.com"),
                Friend(2, "John Doe", "john.doe", "john.doe@test.com"),
        )
        listAdapter.notifyDataSetChanged()
    }
}