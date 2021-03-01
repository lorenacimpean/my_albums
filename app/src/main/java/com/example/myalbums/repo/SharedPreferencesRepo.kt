package com.example.myalbums.repo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.myalbums.ui.contact_info.UserInfo
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable

class SharedPreferencesRepo(val sharedPref: SharedPreferences) {

    private val USER_INFO = "userInfo"

    fun saveUserInfo(userInfo: UserInfo) {
        val json = Gson().toJson(userInfo)
        sharedPref.edit()
            .putString(USER_INFO, json)
            .apply()

    }

    fun getUserInfoFromSharedPreferences(): Observable<UserInfo?> {
        val json = sharedPref.getString(USER_INFO, "")
        val result = Gson().fromJson(json, UserInfo::class.java)
        return Observable.just(result)
    }

}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("default", Context.MODE_PRIVATE)
}