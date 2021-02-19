package com.example.myalbums.endpoint

import com.example.myalbums.models.Photo
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val PATH = "/albums/"
private const val PHOTOS = "/photos/"
private const val HEADERS = "Content-Type:application/json"

interface PhotosService {

    @GET("$PATH{albumId}$PHOTOS")
    @Headers(HEADERS)
    fun fetchPhotosForAlbum(@Path(value = "albumId") albumId: Int): Single<Response<List<Photo>>>

    @GET("$PHOTOS{photoId}")
    @Headers(HEADERS)
    fun fetchPhotoWithId(@Path("photoId") photoId: Int): Single<Response<Photo>>

}