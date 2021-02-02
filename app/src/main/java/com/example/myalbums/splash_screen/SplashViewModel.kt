package com.example.myalbums.splash_screen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


class SplashViewModel(
    val input: Input
) : ViewModel() {


    val output: Output by lazy {
        val nextScreen = input.onStart.flatMap {
            Observable.just(true)
        }
        Output(nextScreen)
    }
}


data class Output(
    val nextScreen: Observable<Boolean>
)

data class Input(
    val onStart: PublishSubject<Boolean>
)
