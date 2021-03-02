package com.example.myalbums.di

import androidx.appcompat.app.AppCompatActivity
import com.example.myalbums.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class DisposableActivity : AppCompatActivity() {

    private val subscriptions: CompositeDisposable = CompositeDisposable()
    fun setUpBackButton() {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    fun disposeLater(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }

}