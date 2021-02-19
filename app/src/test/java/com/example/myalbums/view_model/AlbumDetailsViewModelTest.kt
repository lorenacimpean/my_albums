package com.example.myalbums.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myalbums.models.Album
import com.example.myalbums.models.Photo
import com.example.myalbums.repo.PhotosRepo
import com.example.myalbums.ui.album_details.AlbumDetailsItem
import com.example.myalbums.ui.album_details.AlbumDetailsItem.Companion.HEADER
import com.example.myalbums.ui.album_details.AlbumDetailsItem.Companion.PHOTO
import com.example.myalbums.ui.album_details.AlbumDetailsViewModel
import com.example.myalbums.ui.album_details.HeaderModel
import com.example.myalbums.ui.album_details.Input
import com.example.myalbums.utils.RxOnItemClickListener
import com.example.myalbums.utils.State
import com.example.myalbums.utils.UiModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.subjects.PublishSubject
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class AlbumDetailsViewModelTest {

    @Mock
    private lateinit var repo: PhotosRepo
    private lateinit var vm: AlbumDetailsViewModel
    private lateinit var input: Input

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repo = mock<PhotosRepo>()
        input =
            Input(PublishSubject.create<Album>(), RxOnItemClickListener<AlbumDetailsItem>())
        vm = AlbumDetailsViewModel(input, repo)
    }

    private val album = Album(1, 2, "title")
    private val photo = Photo(album.id, 2, "test photo", "url", "thumbUrl")
    private val header = HeaderModel(album, 1)
    private val itemPhoto = AlbumDetailsItem(type = PHOTO, photo = photo, header = null)
    private val itemHeader = AlbumDetailsItem(type = HEADER, photo = null, header = header)
    private val items = listOf<AlbumDetailsItem>(itemHeader, itemPhoto)
    private val photoList = listOf<Photo>(photo)
    private val apiResp: Response<List<Photo>> = Response.success(photoList)

    @Test
    fun `check data output not null`() {
        val testObserver = TestObserver<UiModel<List<AlbumDetailsItem>>>()
        whenever(repo.getPhotosForAlbum(album.id)).thenReturn(Single.just(apiResp))
        vm.output.onDataFetched.subscribe(testObserver)
        vm.input.loadData.onNext(album)

        val result = testObserver.values()[0]
        Assert.assertNotNull(result)
        testObserver.assertNoErrors()

    }

    @Test
    fun `check data output values`() {
        val testObserver = TestObserver<UiModel<List<AlbumDetailsItem>>>()
        val expected = UiModel.success(items)
        whenever(repo.getPhotosForAlbum(album.id)).thenReturn(Single.just(apiResp))

        vm.output.onDataFetched.subscribe(testObserver)
        vm.input.loadData.onNext(album)

        val loading = testObserver.values()[0]
        val value = testObserver.values()[1]
        testObserver.assertValueCount(2)
        assertEquals(expected, value)
        assertEquals(State.LOADING, loading.state)
        assertEquals(null, loading.data)
    }

    @Test
    fun `check data output error`() {
        val testObserver = TestObserver<UiModel<List<AlbumDetailsItem>>>()
        val error = Exception("error")

        whenever(repo.getPhotosForAlbum(album.id)).thenReturn(Single.error(error))

        vm.output.onDataFetched.subscribe(testObserver)
        vm.input.loadData.onNext(album)

        val loading = testObserver.values()[0]
        val value = testObserver.values()[1]
        testObserver.assertValueCount(2)
        assertEquals(State.LOADING, loading.state)
        assertEquals(null, loading.data)
        assertEquals(null, loading.error)
        assertEquals(State.ERROR, value.state)
        assertEquals(null, value.data)
        assertEquals(error.localizedMessage, value.error)
    }

    @Test
    fun `check click output not null`() {
        val testObserver = TestObserver<Photo?>()
        val album = Album(1, 2, "title")

        vm.output.onPhotoClicked.subscribe(testObserver)
        vm.input.clickOnItem.onItemClick(itemPhoto)

        Assert.assertNotNull(album)
        testObserver.assertNoErrors()

    }

    @Test
    fun `check click output empty`() {
        val testObserver = TestObserver<Photo?>()

        vm.output.onPhotoClicked.subscribe(testObserver)
        vm.input.clickOnItem.onItemClick(itemHeader)

        testObserver.assertNoErrors()
        testObserver.assertEmpty()

    }

    @Test
    fun `check click output check values`() {
        val testObserver = TestObserver<Photo?>()

        vm.output.onPhotoClicked.subscribe(testObserver)
        vm.input.clickOnItem.onItemClick(itemPhoto)

        val result = testObserver.values()[0]
        testObserver.assertValueCount(1)
        assertEquals(photo, result)
    }
}