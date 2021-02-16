package com.example.myalbums.utils

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

fun <T> Observable<T>.subscribeOnMainThread(onNext: (it: T) -> Unit): Disposable =
    observeOn(AndroidSchedulers.mainThread())
        .subscribe({
                       onNext(it)
                   }, { error ->
                       Log.e(
                           "subscribeOnMainThread",
                           "SUBSCRIBE FAILED, $error")

                   })



