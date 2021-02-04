package com.example.myalbums.di

import com.example.myalbums.splash_screen.Input
import com.example.myalbums.splash_screen.SplashViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// view model di
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}

val viewModelInputModule = module {
    single { Input(get()) }
}

val subjectModule = module {
    single { PublishSubject.create<Boolean>() }
}

// repo di


// endpoint di