package com.example.myalbums.ui.album_details

import androidx.lifecycle.ViewModel
import com.example.myalbums.models.Album
import com.example.myalbums.models.AlbumDetailsItem
import com.example.myalbums.repo.PhotosRepo
import com.example.myalbums.ui.home_screen.ItemType
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class AlbumDetailsViewModel(val input: Input, private val photosRepo: PhotosRepo) : ViewModel() {

    val output: Output by lazy {
        val albums =
            input.loadData.flatMap {
                val list = mutableListOf<AlbumDetailsItem>()
                list.add(AlbumDetailsItem(ItemType.HEADER, header = it))
                photosRepo.getPhotosForAlbum(it.id)
                    .toObservable()
                    .map { photoList ->
                        photoList.body()
                            ?.map { photo ->
                                list.add(AlbumDetailsItem(ItemType.PHOTO, photo = photo))
                            }
                        return@map UiModel.success(list.toList())
                    }

            }
                .startWith(Observable.just(UiModel.loading()))
                .onErrorReturn { UiModel.error(it.localizedMessage) }

        val albumClicked = input.clickOnItem.map {
            return@map UiModel.success(it)
        }
        Output(albums, albumClicked)
    }

}

data class Output(
        val onDataFetched: Observable<UiModel<List<AlbumDetailsItem>>>,
        val onItemClicked: Observable<UiModel<AlbumDetailsItem>>)

data class Input(
        val loadData: PublishSubject<Album>,
        val clickOnItem: PublishSubject<AlbumDetailsItem>)

