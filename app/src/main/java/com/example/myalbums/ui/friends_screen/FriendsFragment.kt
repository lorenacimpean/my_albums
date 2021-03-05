package com.example.myalbums.ui.friends_screen

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentFriendsBinding
import com.example.myalbums.di.BaseFragment
import com.example.myalbums.models.Friend
import com.example.myalbums.utils.State
import com.example.myalbums.utils.UiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FriendsFragment : BaseFragment() {

    private val viewModel : FriendsViewModel by viewModel<FriendsViewModel>()
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

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val friendsListener : MutableLiveData<UiModel<List<Friend>>> = viewModel.getFriends()
        friendsListener.observe(viewLifecycleOwner, { response ->
            when (response.state) {
                State.SUCCESS -> response.data?.let {
                    listAdapter.friendsList = response.data
                    listAdapter.notifyDataSetChanged()
                }
                State.LOADING -> print("LOADING")
                State.ERROR -> Toast.makeText(this.context, response.error, Toast.LENGTH_SHORT)
                        .show()
            }
        })
    }
    //    override fun onStart() {
    //        super.onStart()
    //        listAdapter.friendsList = listOf(
    //                Friend(1, "Jane Doe", "jane.doe", "jane.doe@test.com"),
    //                Friend(2, "John Doe", "john.doe", "john.doe@test.com"),
    //        )
    //        listAdapter.notifyDataSetChanged()
    //    }
}