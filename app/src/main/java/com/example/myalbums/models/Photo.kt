package com.example.myalbums.models

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("userId")
        val albumId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String) {

    val albumIdString: String
        get() = albumId.toString()
    val photoId: String
        get() = id.toString()

}

