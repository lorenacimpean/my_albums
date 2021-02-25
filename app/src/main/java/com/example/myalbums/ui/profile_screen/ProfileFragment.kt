package com.example.myalbums.ui.profile_screen

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentProfileBinding
import com.example.myalbums.di.BaseFragment
import com.example.myalbums.ui.contact_info.ContactDetailsActivity
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    private val viewModel: ProfileViewModel by viewModel<ProfileViewModel>()
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        super.enableBackButton()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.listener = viewModel.input.openContactInfo
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        disposeLater(viewModel.output.onContactInfoTapped.subscribeOnMainThread { _ ->
            activity?.let {
                val intent = Intent(it, ContactDetailsActivity::class.java)
                it.startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        enableOptionMenu(menu, inflater)
        super.onCreateOptionsMenu(menu, inflater)
    }
}