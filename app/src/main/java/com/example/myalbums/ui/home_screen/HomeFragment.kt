package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentHomeBinding
import com.example.myalbums.di.DisposableFragment
import com.example.myalbums.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : DisposableFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToAlbumsList()
        viewModel.input.onFragmentStart.onNext(true)

    }

    override fun onStart() {
        super.onStart()

    }

    private fun listenToAlbumsList() {
        disposeLater(viewModel.output.albumsFetched
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribe { response ->
                             when (response.state) {
                                 State.SUCCESS -> response.data?.let {
                                     binding.albumsRecyclerView.addAlbums(it)
                                 }
                                 State.LOADING -> print("LOADING")
                                 State.ERROR   -> print("ERROR")
                             }
                         })
    }

}