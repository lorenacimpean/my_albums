package com.example.myalbums.ui.friends_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myalbums.models.Friend
import com.example.myalbums.repo.FriendsRepo
import com.example.myalbums.utils.UiModel
import com.example.myalbums.utils.subscribeOnMainThread

class FriendsViewModel(private val repo : FriendsRepo) : ViewModel() {

    private val friendsList : MutableLiveData<UiModel<List<Friend>>> by lazy {
        MutableLiveData<UiModel<List<Friend>>>()
    }

    fun getFriends() : MutableLiveData<UiModel<List<Friend>>> {
        repo.getFriends()
                .toObservable()
                .subscribeOnMainThread {
                    friendsList.value = UiModel.loading()
                    if (it.isSuccessful) {
                        friendsList.value = UiModel.success(it.body())
                    } else {
                        friendsList.value = UiModel.error(it.message())
                    }
                }
        return friendsList
    }
}