package com.example.myalbums.ui.home_screen

import androidx.lifecycle.ViewModel
import com.example.myalbums.models.Album
import com.example.myalbums.repo.AlbumsRepo
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class HomeViewModel(val input: Input, private val albumsRepo: AlbumsRepo) : ViewModel() {

    val output: Output by lazy {
        val albums =
            input.onLoadData.flatMap {
                albumsRepo.getAlbums()
                    .toObservable()
                    .map {
                        return@map UiModel.success(it.body())
                    }

            }
                .startWith(Observable.just(UiModel.loading()))
                .onErrorReturn { UiModel.error(it.localizedMessage) }
        val albumClicked = input.onAlbumClick.map {
            return@map it
        }
        Output(albums, albumClicked)
    }

}

data class Output(
        val albumsFetched: Observable<UiModel<List<Album>>>,
        val albumClicked: Observable<Album>)

data class Input(
        val onLoadData: PublishSubject<Boolean>,
        val onAlbumClick: PublishSubject<Album>)

