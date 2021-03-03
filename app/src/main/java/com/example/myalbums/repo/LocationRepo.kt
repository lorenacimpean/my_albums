package com.example.myalbums.repo

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.rxjava3.core.Observable

class LocationRepo(private val fusedLocationClient: FusedLocationProviderClient) {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Observable<Location> {
        Log.d(TAG, fusedLocationClient.lastLocation.result.latitude.toString())
        Log.d(TAG, fusedLocationClient.lastLocation.result.longitude.toString())
        return Observable.just(fusedLocationClient.lastLocation.result)

    }

    companion object {

        private const val TAG = "LocationRepo"
    }

}

@SuppressLint("VisibleForTests")
fun provideLocationRepo(application: Application): FusedLocationProviderClient {
    return FusedLocationProviderClient(application.baseContext)
}