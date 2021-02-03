package com.example.myalbums.di

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class DisposableActivity : AppCompatActivity() {
    private val subscriptions: CompositeDisposable = CompositeDisposable()

    fun disposeLater(disposable: Disposable) {
        subscriptions.add(disposable)
    }


    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}