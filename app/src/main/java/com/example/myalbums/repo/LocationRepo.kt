package com.example.myalbums.repo

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.myalbums.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.rxjava3.core.Single

class LocationRepo(private val fusedLocationClient : FusedLocationProviderClient,
                   private val application : Application) {

    fun getCurrentLocation() : Single<Location> {
        if (ActivityCompat.checkSelfPermission(application.applicationContext,
                                               Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            return Single.error(LocationPermissionError(application.applicationContext.getString(R.string.permission_denied)))
        } else {
            fusedLocationClient.flushLocations()
            return Single.create<Location> { emitter ->
                val task = fusedLocationClient.lastLocation
                task.addOnSuccessListener { location : Location? ->
                    if (location != null) {
                        emitter.onSuccess(location)
                    }
                }
            }
        }
    }
}

fun provideLocationRepo(application : Application) : FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(application.applicationContext)
}

data class LocationPermissionError(var error : String) : Throwable()