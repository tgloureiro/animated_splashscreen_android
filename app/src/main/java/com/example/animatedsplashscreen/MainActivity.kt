package com.example.animatedsplashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider

class MainActivity : AppCompatActivity() {
    companion object{
        const val splashFadeDurationMillis = 500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.iconView
                .animate()
                .setDuration(splashFadeDurationMillis.toLong())
                .alpha(0f)
                .withEndAction {
                    splashScreenViewProvider.remove()
                    setContentView(R.layout.activity_main)
                }.start()
        }
    }
}