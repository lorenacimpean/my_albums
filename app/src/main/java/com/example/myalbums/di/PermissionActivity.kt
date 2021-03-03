package com.example.myalbums.di

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlin.properties.Delegates

open class PermissionActivity : DisposableActivity() {

    var isLocationPermissionGranted by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        isLocationPermissionGranted = ActivityCompat.checkSelfPermission(this,
                                                                         Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                                          REQUEST_LOCATION)
    }

    override fun onRequestPermissionsResult(requestCode : Int, permissions : Array<String>, grantResults : IntArray) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.size == 2 && grantResults.all {
                    it == PackageManager.PERMISSION_GRANTED
                }) {
                isLocationPermissionGranted = true
                Log.d(TAG, "Permission granted")
            } else {
                isLocationPermissionGranted = false
                Log.e(TAG, "Location permission denied")
            }
        }
    }

    companion object {

        private const val TAG = "PermissionActivity"
        private const val REQUEST_LOCATION = 1
    }
}