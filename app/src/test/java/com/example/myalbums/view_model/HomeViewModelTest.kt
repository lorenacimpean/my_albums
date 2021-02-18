package com.example.myalbums.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myalbums.models.Album
import com.example.myalbums.repo.AlbumsRepo
import com.example.myalbums.ui.home_screen.HomeViewModel
import com.example.myalbums.ui.home_screen.Input
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

class HomeViewModelTest {

    @Mock
    private lateinit var repo: AlbumsRepo
    private lateinit var vm: HomeViewModel
    private lateinit var input: Input

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repo = mock<AlbumsRepo>()
        input = Input(PublishSubject.create<Boolean>())
        vm = HomeViewModel(input, repo)
    }

    @Test
    fun `check output not null`() {
        val testObserver = TestObserver<UiModel<List<Album>>>()
        val albums = listOf(Album(1, 2, "title"))
        val resp = Response.success(albums)

        whenever(repo.getAlbums()).thenReturn(Single.just(resp))
        vm.output.albumsFetched.subscribe(testObserver)
        vm.input.onLoadData.onNext(true)

        val result = testObserver.values()[0]
        Assert.assertNotNull(result)
        testObserver.assertNoErrors()

    }

    @Test
    fun `check output check values`() {
        val testObserver = TestObserver<UiModel<List<Album>>>()
        val albums = listOf(Album(1, 2, "title"))
        val resp = Response.success(albums)
        val expected = UiModel.success(albums)


        whenever(repo.getAlbums()).thenReturn(Single.just(resp))
        vm.output.albumsFetched.subscribe(testObserver)
        vm.input.onLoadData.onNext(true)

        val loading = testObserver.values()[0]
        val value = testObserver.values()[1]
        testObserver.assertValueCount(2)
        assertEquals(expected, value)
        assertEquals(State.LOADING, loading.state)
        assertEquals(null, loading.data)

    }

    @Test
    fun `check output check error`() {
        val testObserver = TestObserver<UiModel<List<Album>>>()
        val error = Exception("error")

        whenever(repo.getAlbums()).thenReturn(Single.error(error))
        vm.output.albumsFetched.subscribe(testObserver)
        vm.input.onLoadData.onNext(true)

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

}