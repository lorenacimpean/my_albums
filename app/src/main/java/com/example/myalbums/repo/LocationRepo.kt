package com.example.myalbums.repo

import android.Manifest
import android.annotation.SuppressLint
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

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() : Single<Location> {
        fusedLocationClient.flushLocations()
        return Single.create { emitter ->
            val task = fusedLocationClient.lastLocation
            task.addOnSuccessListener { location : Location? ->
                if (location != null) {
                    emitter.onSuccess(location)
                }
            }
        }
    }

    fun getLocationPermission() : Single<MissingPermissionsError> {
        return if (ActivityCompat.checkSelfPermission(application.applicationContext,
                                                      Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            Single.just(MissingPermissionsError(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                                    application.applicationContext.getString(R.string.permission_denied)))
        } else {
            Single.just(MissingPermissionsError())
        }
    }
}

fun provideLocationRepo(application : Application) : FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(application.applicationContext)
}

data class MissingPermissionsError(val missingPermissions : Array<String>? = null,
                                   val message : String? = null) {

    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MissingPermissionsError

        if (!missingPermissions.contentEquals(other.missingPermissions)) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode() : Int {
        var result = missingPermissions.contentHashCode()
        result = 31 * result + message.hashCode()
        return result
    }
}