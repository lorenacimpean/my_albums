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
            input.onStart.flatMap {
                albumsRepo.getAlbums()
                    .map {
                        return@map UiModel.success(it.body())
                    }
                    .onErrorReturn { UiModel.error(it.localizedMessage) }
            }
        Output(albums)
    }
}

data class Output(
        val albums: Observable<UiModel<List<Album>>>
)

data class Input(
        val onStart: PublishSubject<Boolean>
)