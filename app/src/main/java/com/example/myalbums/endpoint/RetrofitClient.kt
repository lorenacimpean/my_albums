package com.example.myalbums.endpoint

import com.example.myalbums.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient : OkHttpClient) : Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
}

fun provideOkHttpClient(
    loggingInterceptor : HttpLoggingInterceptor
) : OkHttpClient {
    return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
}

fun provideLoggingInterceptor() : HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return logger
}

fun provideAlbumsApi(retrofit : Retrofit) : AlbumsService = retrofit.create(AlbumsService::class.java)
fun providePhotosApi(retrofit : Retrofit) : PhotosService = retrofit.create(PhotosService::class.java)
fun provideFriendsApi(retrofit : Retrofit) : FriendsService = retrofit.create(FriendsService::class.java)
