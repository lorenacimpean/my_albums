package com.example.myalbums.di

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class DisposableFragment : Fragment() {
    private val subscriptions: CompositeDisposable = CompositeDisposable()

    fun disposeLater(disposable: Disposable) {
        subscriptions.add(disposable)
    }


    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}