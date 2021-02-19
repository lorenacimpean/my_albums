package com.example.myalbums.repo

import com.example.myalbums.endpoint.PhotosService
import com.example.myalbums.models.Photo
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class PhotosRepo(private val photosService: PhotosService) {

    fun getPhotosForAlbum(albumId: Int): Single<Response<List<Photo>>> {
        return photosService.fetchPhotosForAlbum(albumId)
    }

    fun getPhotoWIthId(photoId: Int): Single<Response<Photo>> {
        return photosService.fetchPhotoWithId(photoId)
    }
}