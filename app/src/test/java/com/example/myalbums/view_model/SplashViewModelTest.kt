package com.example.myalbums.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myalbums.ui.splash_screen.Input
import com.example.myalbums.ui.splash_screen.SplashViewModel
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest


class SplashViewModelTest : KoinTest {

    private lateinit var vm: SplashViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val input = Input(PublishSubject.create<Boolean>())
        vm = SplashViewModel(input)

    }


    @Test
    fun `check onAnimationEnded not null`() {
        val testObserver = TestObserver<Boolean>()
        vm.output.nextScreen.subscribe(testObserver)
        vm.input.onAnimationEnd.onNext(true)
        val result = testObserver.values()[0]

        assertNotNull(result)
        testObserver.assertNoErrors()

    }

    @Test
    fun `check onAnimationEnded value`() {
        val testObserver = TestObserver<Boolean>()
        vm.output.nextScreen.subscribe(testObserver)
        vm.input.onAnimationEnd.onNext(true)
        val result = testObserver.values()[0]


        testObserver.assertValueCount(1)
        assertEquals(true, result)

    }
}