package com.example.myalbums.endpoint

import com.example.myalbums.models.Friend
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

private const val PATH = "/users/"
private const val HEADERS = "Content-Type:application/json"

interface FriendsService {

    @GET(PATH)
    @Headers(HEADERS)
    fun fetchFriends() : Single<Response<List<Friend>>>
}

