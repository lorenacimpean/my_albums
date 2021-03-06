package com.example.myalbums.ui.friends_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myalbums.models.Friend
import com.example.myalbums.repo.FriendsRepo

class FriendsViewModel(private val repo : FriendsRepo,
                       application : Application) : AndroidViewModel(application) {

    fun getFriends() : LiveData<List<Friend>?> {
        return repo.getFriends()
    }
}