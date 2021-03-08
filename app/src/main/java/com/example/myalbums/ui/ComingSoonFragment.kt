package com.example.myalbums.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentComingSoonBinding
import com.example.myalbums.di.BaseFragment

class ComingSoonFragment : BaseFragment() {

    private lateinit var binding : FragmentComingSoonBinding
    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?,
                              savedInstanceState : Bundle?) : View? {
        super.enableBackButton()
        binding = DataBindingUtil.inflate(inflater,
                                          R.layout.fragment_coming_soon,
                                          container,
                                          false)

        return binding.root
    }
}