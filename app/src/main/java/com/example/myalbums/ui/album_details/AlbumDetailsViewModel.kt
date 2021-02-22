package com.example.myalbums.ui.album_details

import androidx.annotation.IntDef
import androidx.lifecycle.ViewModel
import com.example.myalbums.models.Album
import com.example.myalbums.models.Photo
import com.example.myalbums.repo.PhotosRepo
import com.example.myalbums.ui.album_details.AlbumDetailsItem.Companion.HEADER
import com.example.myalbums.ui.album_details.AlbumDetailsItem.Companion.PHOTO
import com.example.myalbums.utils.RxOnItemClickListener
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class AlbumDetailsViewModel(val input: Input, private val photosRepo: PhotosRepo) : ViewModel() {

    private lateinit var photos: List<Photo>
    val output: Output by lazy {
        val items =
            input.loadData.flatMap { album ->
                val list = mutableListOf<AlbumDetailsItem>()

                photosRepo.getPhotosForAlbum(album.id)
                    .toObservable()
                    .map { response ->
                        response.body()
                            ?.let { photoList ->
                                photos = photoList
                                val header = HeaderModel(album, photoList.size)
                                list.add(AlbumDetailsItem(HEADER, header = header, photo = null))
                                photoList.map { photo ->
                                    list.add(AlbumDetailsItem(PHOTO, photo = photo, header = null))
                                }
                            }
                        return@map UiModel.success(list.toList())
                    }
                    .startWith(Observable.just(UiModel.loading()))
                    .onErrorReturn { UiModel.error(it.localizedMessage) }
            }

        val photoClicked = input.clickOnItem.rx.flatMap { item ->
            return@flatMap if (item.type == PHOTO) {
                Observable.just(photos)
            }
            else Observable.empty()
        }
        Output(items, photoClicked)
    }

}

data class Output(
        val onDataFetched: Observable<UiModel<List<AlbumDetailsItem>>>,
        val onPhotoClicked: Observable<List<Photo>>)

data class Input(
        val loadData: PublishSubject<Album>,
        val clickOnItem: RxOnItemClickListener<AlbumDetailsItem>)

data class AlbumDetailsItem(var type: Int, var photo: Photo? = null, var header: HeaderModel? = null) {
    companion object {

        @IntDef(HEADER, PHOTO)
        @Retention(AnnotationRetention.SOURCE)
        annotation class ItemType

        const val HEADER = 0
        const val PHOTO = 1

    }

}

data class HeaderModel(val album: Album, val photoCount: Int) {

    val photoCountString: String
        get() = photoCount.toString()

}