package com.example.myalbums.endpoint

import com.example.myalbums.models.Album
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val PATH = "/albums/"
private const val HEADERS = "Content-Type:application/json"

interface AlbumsService {

    @GET(PATH)
    @Headers(HEADERS)
    fun fetchAlbums(): Single<Response<List<Album>>>

    @GET(PATH)
    @Headers(HEADERS)
    fun fetchAlbumWithId(@Query("albumId") albumId: Int): Single<Response<Album>>
}
