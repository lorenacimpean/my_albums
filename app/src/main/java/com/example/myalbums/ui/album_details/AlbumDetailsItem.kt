package com.example.myalbums.ui.album_details

import com.example.myalbums.models.Photo
import com.example.myalbums.ui.home_screen.ItemType

data class AlbumDetailsItem(var type: ItemType, var photo: Photo? = null, var header: HeaderModel? = null)