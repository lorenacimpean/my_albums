package com.example.myalbums.models

import com.google.gson.annotations.SerializedName

data class Album(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String) {

        val userIdString: String
                get() = userId.toString()
        val albumIdString: String
                get() = id.toString()

}

