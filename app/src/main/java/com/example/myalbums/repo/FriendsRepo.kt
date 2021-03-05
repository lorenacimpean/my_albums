package com.example.myalbums.repo

import com.example.myalbums.endpoint.FriendsService
import com.example.myalbums.models.Friend
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class FriendsRepo(private val friendsService : FriendsService) {

    fun getFriends() : Single<Response<List<Friend>>> {
        return friendsService.fetchFriends()
    }
}

