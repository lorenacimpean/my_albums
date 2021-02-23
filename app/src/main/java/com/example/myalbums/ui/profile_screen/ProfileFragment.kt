package com.example.myalbums.ui.profile_screen

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentProfileBinding
import com.example.myalbums.di.BaseFragment

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        super.enableBackButton()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        enableOptionMenu(menu, inflater)
        super.onCreateOptionsMenu(menu, inflater)
    }
}