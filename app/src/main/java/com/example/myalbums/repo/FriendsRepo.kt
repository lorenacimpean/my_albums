package com.example.myalbums.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myalbums.endpoint.FriendsService
import com.example.myalbums.models.Friend
import retrofit2.*

class FriendsRepo(private val friendsService : FriendsService) {

    fun getFriends() : LiveData<List<Friend>?> {
        val data : MutableLiveData<List<Friend>?> = MutableLiveData<List<Friend>?>()
        friendsService.fetchFriends()
                .enqueue(object : Callback<List<Friend>?> {
                    override fun onResponse(call : Call<List<Friend>?>?, response : Response<List<Friend>?>) {
                        data.value = response.body()
                    }

                    override fun onFailure(call : Call<List<Friend>?>?, t : Throwable?) {
                        data.value = null
                    }
                })
        return data
    }
}

