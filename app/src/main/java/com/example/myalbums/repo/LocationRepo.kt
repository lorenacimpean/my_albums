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

class LocationRepo(
    private val fusedLocationClient : FusedLocationProviderClient,
    private val application : Application
) {

    fun getCurrentLocation() : Single<Location> {
        return if (ActivityCompat.checkSelfPermission(
                    application.applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            Single.error(MissingPermissionsError(message = application.getString(R.string.permission_denied)))
        } else {
            fusedLocationClient.flushLocations()
            Single.create { emitter ->
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

class MissingPermissionsError(
    var missingPermissions : Array<String>? = null,
    override var message : String? = null,
) : Throwable(message) {

    init {
        missingPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    companion object {

        fun fromThrowable(error : Throwable?) : MissingPermissionsError {
            return MissingPermissionsError(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), error?.localizedMessage)
        }
    }
}