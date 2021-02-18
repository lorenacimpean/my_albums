package com.example.myalbums.di

import com.example.myalbums.endpoint.*
import com.example.myalbums.models.Album
import com.example.myalbums.repo.AlbumsRepo
import com.example.myalbums.repo.PhotosRepo
import com.example.myalbums.ui.album_details.AlbumDetailsItem
import com.example.myalbums.ui.album_details.AlbumDetailsViewModel
import com.example.myalbums.ui.home_screen.HomeViewModel
import com.example.myalbums.ui.splash_screen.SplashViewModel
import com.example.myalbums.utils.RxOnItemClickListener
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.myalbums.ui.album_details.Input as detailsInput
import com.example.myalbums.ui.home_screen.Input as homeInput
import com.example.myalbums.ui.splash_screen.Input as splashInput

// view model di
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AlbumDetailsViewModel(get(), get()) }
}

// view model input di
val viewModelInputModule = module {
    single { splashInput(get()) }
    single { homeInput(get(), get()) }
    single { detailsInput(get(), get()) }
}

// view model subjects di
val subjectModule = module {
    factory {
        PublishSubject.create<Boolean>()
        PublishSubject.create<Album>()
        PublishSubject.create<AlbumDetailsItem>()
    }
}

// view model clicks di
val itemClicksModule = module {
    factory {
        RxOnItemClickListener<Album>()
    }
}

// repo di
val repoModule = module {
    factory { AlbumsRepo(get()) }
    factory { PhotosRepo(get()) }
}

// endpoint di
val apiModule = module {
    factory { provideOkHttpClient(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }
    factory { provideAlbumsApi(get()) }
    factory { providePhotosApi(get()) }
}
