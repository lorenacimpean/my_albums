package com.example.myalbums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myalbums.splash_screen.Input
import com.example.myalbums.splash_screen.SplashViewModel
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mockito.spy


class SplashViewModelTest : KoinTest {


    private lateinit var vm: SplashViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val input = Input(PublishSubject.create<Boolean>())
        vm = spy(
            SplashViewModel(input)
        )

    }

    @Test
    fun `check onStart`() {
        val testObserver = TestObserver<Boolean>()
        vm.output.nextScreen.subscribe(testObserver)
        vm.input.onStart.onNext(true)
        val result = testObserver.values()[0]

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        assertNotNull(result)
        assertEquals(true, result)


    }
}