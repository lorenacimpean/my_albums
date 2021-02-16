package com.example.myalbums.ui.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myalbums.R
import com.example.myalbums.databinding.FragmentAlbumDetailsBinding
import com.example.myalbums.models.Album

class AlbumDetailsFragment : Fragment() {

    private val args: AlbumDetailsFragmentArgs by navArgs()
    private lateinit var album: Album
    private lateinit var binding: FragmentAlbumDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album_details, container, false)
        return binding.root
    }

}