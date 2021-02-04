package com.example.myalbums.endpoint

import com.example.myalbums.models.Album
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

const val BASE_URL = "https://jsonplaceholder.typicode.com"
const val PATH = "/albums/"
const val HEADERS = "Content-Type:application/json"

interface AlbumsService {

    @GET(PATH)
    @Headers(HEADERS)
    fun fetchAlbums(): Observable<Response<List<Album>>>
}
