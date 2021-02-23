package com.example.myalbums.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
        val thumbnailUrl: String) : Parcelable {

    val albumIdString: String
        get() = albumId.toString()
    val photoId: String
        get() = id.toString()

}

