package com.example.myalbums.ui.friends_screen

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentFriendsBinding
import com.example.myalbums.di.BaseFragment
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
        val clickListener = LiveDataClickListener { id ->
            viewModel.clickItemWithId(id)
                    .observe(
                            viewLifecycleOwner) {
                        Toast.makeText(this.context, "TAPPED ON CELL WITH $id", Toast.LENGTH_SHORT)
                                .show()
                    }
        }
        listAdapter = FriendsAdapter(clickListener)
        binding.friendsRecyclerView.adapter = listAdapter
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFriends()
                .observe(viewLifecycleOwner, { friends ->
                    if (friends != null) {
                        listAdapter.friendsList = friends
                        listAdapter.notifyDataSetChanged()
                    }
                })
    }
}