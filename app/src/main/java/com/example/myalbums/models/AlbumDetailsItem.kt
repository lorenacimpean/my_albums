package com.example.myalbums.models

import com.example.myalbums.ui.home_screen.ItemType

data class AlbumDetailsItem(var type: ItemType, var photo: Photo? = null, var header: Album? = null)