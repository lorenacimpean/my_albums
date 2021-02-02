package com.example.myalbums.splash_screen

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myalbums.MainActivity
import com.example.myalbums.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashScreen : AppCompatActivity() {
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
                viewModel.input.onStart.onNext(true)
            }
        })
    }

    private fun listenToNextScreen() {
        viewModel.output.nextScreen.subscribe {
            if (it) {
                goToNextScreen()
            }
        }
    }


    private fun goToNextScreen() {
        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
    }


}