package com.example.myalbums.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myalbums.endpoint.AlbumsService
import com.example.myalbums.models.Album
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response


class AlbumsRepoTest {

    @Mock
    private lateinit var repo: AlbumsRepo


    private lateinit var api: AlbumsService

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        api = mock<AlbumsService>()
        repo = AlbumsRepo(api)

    }


    @Test
    fun `check getAlbums not null`() {
        val albums = listOf(Album(1, 2, "title"))
        val expected = Response.success(albums)
        val testObserver = TestObserver<Response<List<Album>>>()
        whenever(api.fetchAlbums()).thenReturn(Observable.just(expected))
        repo.getAlbums().subscribe(testObserver)
        val result = testObserver.values()[0]


        Assert.assertNotNull(result)
        testObserver.assertNoErrors()

    }

    @Test
    fun `check getAlbums check values`() {
        val albums = listOf(Album(1, 2, "title"))
        val expected = Response.success(albums)
        val testObserver = TestObserver<Response<List<Album>>>()
        whenever(api.fetchAlbums()).thenReturn(Observable.just(expected))
        repo.getAlbums().subscribe(testObserver)
        val result = testObserver.values()[0]

        testObserver.assertValueCount(1)
        assertEquals(expected, result)

    }


}