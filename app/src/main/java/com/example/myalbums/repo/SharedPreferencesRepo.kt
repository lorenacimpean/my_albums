package com.example.myalbums.repo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.myalbums.ui.contact_info.UserInfo
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable

class SharedPreferencesRepo(private val sharedPref : SharedPreferences) {

    fun saveUserInfo(userInfo : UserInfo) : Observable<Boolean> {
        val json = Gson().toJson(userInfo)
        sharedPref.edit()
                .putString(USER_INFO, json)
                .apply()
        return Observable.just(true)
    }

    fun getUserInfoFromSharedPreferences() : Observable<UserInfo> {
        val json = sharedPref.getString(USER_INFO, "")
        var result = Gson().fromJson(json, UserInfo::class.java)
        result = result ?: UserInfo()
        return Observable.just(result)
    }

    companion object {

        private const val USER_INFO = "userInfo"
    }
}

fun getSharedPrefs(androidApplication : Application) : SharedPreferences {
    return androidApplication.getSharedPreferences("default", Context.MODE_PRIVATE)
}