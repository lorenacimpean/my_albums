package com.example.myalbums

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        val logo: ImageView = findViewById(R.id.imgLogo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        val intent = Intent()
        logo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000)


    }
}