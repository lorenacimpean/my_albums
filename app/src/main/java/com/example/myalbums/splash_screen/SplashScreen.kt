package com.example.myalbums.splash_screen

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.myalbums.MainActivity
import com.example.myalbums.R
import com.example.myalbums.di.DisposableActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashScreen : DisposableActivity() {
    private val viewModel: SplashViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        listenToNextScreen()
        startAnimation()
    }

    private fun startAnimation() {
        val logo: ImageView = findViewById(R.id.imgLogo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        logo.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                viewModel.input.onAnimationEnd.onNext(true)
            }
        })
    }


    private fun listenToNextScreen() {
        disposeLater(viewModel.output.nextScreen.subscribe {
            if (it) {
                goToNextScreen()
            }
        })
    }


    private fun goToNextScreen() {
        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }


}