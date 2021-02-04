package com.example.myalbums.di

import com.example.myalbums.endpoint.*
import com.example.myalbums.repo.AlbumsRepo
import com.example.myalbums.ui.home_screen.HomeViewModel
import com.example.myalbums.ui.splash_screen.SplashViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.myalbums.ui.home_screen.Input as homeInput
import com.example.myalbums.ui.splash_screen.Input as splashInput

// view model di
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
}

// view model input di
val viewModelInputModule = module {
    single { splashInput(get()) }
    single { homeInput(get()) }
}

// view model subjects di
val subjectModule = module {
    factory { PublishSubject.create<Boolean>() }
}

// repo di
val repoModule = module {
    factory { AlbumsRepo(get()) }
}

// endpoint di
val apiModule = module {
    factory { AlbumsInterceptor() }
    factory { provideOkHttpClient(get(), get()) }
    factory { provideAlbumsApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }

}