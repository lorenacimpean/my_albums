package com.example.myalbums.repo

import com.example.myalbums.endpoint.AlbumsService
import com.example.myalbums.models.Album
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class AlbumsRepo(private val albumsService: AlbumsService) {

    fun getAlbums(): Single<Response<List<Album>>> {
        return albumsService.fetchAlbums()
    }

    fun getAlbumWithId(albumId: Int): Single<Response<Album>> {
        return albumsService.fetchAlbumWithId(albumId)
    }
}