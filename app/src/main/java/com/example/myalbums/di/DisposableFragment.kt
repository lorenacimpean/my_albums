package com.example.myalbums.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class DisposableFragment : BaseFragment() {
    private val subscriptions: CompositeDisposable = CompositeDisposable()

    fun disposeLater(disposable: Disposable) {
        subscriptions.add(disposable)
    }


    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}