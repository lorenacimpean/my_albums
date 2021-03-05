package com.example.myalbums.models

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("username")
    val username : String,
    @SerializedName("email")
    val email : String
) {

    val userIdString : String
        get() = id.toString()
}