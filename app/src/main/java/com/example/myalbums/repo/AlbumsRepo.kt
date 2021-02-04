package com.example.myalbums.repo

import com.example.myalbums.endpoint.AlbumsService
import com.example.myalbums.models.Album
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

class AlbumsRepo(val albumsService: AlbumsService) {

    fun getAlbums(): @NonNull Observable<Response<List<Album>>> {
        return albumsService.fetchAlbums()

    }
}