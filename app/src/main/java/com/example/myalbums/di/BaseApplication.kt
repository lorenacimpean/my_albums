package com.example.myalbums.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidFileProperties()
            modules(listOf(viewModelModule, viewModelInputModule, subjectModule))
        }
    }



}