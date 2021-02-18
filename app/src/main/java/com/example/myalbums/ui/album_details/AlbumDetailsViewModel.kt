package com.example.myalbums.ui.album_details

import androidx.lifecycle.ViewModel
import com.example.myalbums.models.Album
import com.example.myalbums.models.Photo
import com.example.myalbums.repo.PhotosRepo
import com.example.myalbums.ui.home_screen.ItemType
import com.example.myalbums.utils.RxOnItemClickListener
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class AlbumDetailsViewModel(val input: Input, private val photosRepo: PhotosRepo) : ViewModel() {

    val output: Output by lazy {
        val items =
            input.loadData.flatMap { album ->
                val list = mutableListOf<AlbumDetailsItem>()

                photosRepo.getPhotosForAlbum(album.id)
                    .toObservable()
                    .map { response ->
                        response.body()
                            ?.let { photoList ->
                                val header = HeaderModel(album, photoList.size)
                                list.add(AlbumDetailsItem(ItemType.HEADER, header = header, photo = null))
                                photoList.map { photo ->
                                    list.add(AlbumDetailsItem(ItemType.PHOTO, photo = photo, header = null))
                                }
                            }
                        return@map UiModel.success(list.toList())
                    }
                    .startWith(Observable.just(UiModel.loading()))
                    .onErrorReturn { UiModel.error(it.localizedMessage) }
            }

        val photoClicked = input.clickOnItem.rx.map {
            return@map it
        }
        Output(items, photoClicked)
    }

}

data class Output(
        val onDataFetched: Observable<UiModel<List<AlbumDetailsItem>>>,
        val onPhotoClicked: Observable<Photo>)

data class Input(
        val loadData: PublishSubject<Album>,
        val clickOnItem: RxOnItemClickListener<Photo>)

