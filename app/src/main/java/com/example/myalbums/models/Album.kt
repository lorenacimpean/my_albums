package com.example.myalbums.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlin.math.absoluteValue

@Parcelize
data class Album(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String) : Parcelable {

        val albumTitle: String get() = title
        val userIdString: String
                get() = userId.absoluteValue.toString()
        val albumIdString: String
                get() = id.absoluteValue.toString()

}

