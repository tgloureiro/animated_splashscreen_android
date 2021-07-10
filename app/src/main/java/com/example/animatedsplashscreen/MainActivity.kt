package com.example.animatedsplashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    companion object{
        const val splashFadeDurationMillis = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashWasDisplayed = savedInstanceState != null
        if(!splashWasDisplayed){
            val splashScreen = installSplashScreen()


            splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
                // Get icon instance and start a fade out animation
                splashScreenViewProvider.iconView
                    .animate()
                    .setDuration(splashFadeDurationMillis.toLong())
                    .alpha(0f)
                    .withEndAction {
                        // After the fade out, remove the splash and set content view
                        splashScreenViewProvider.remove()
                        setContentView(R.layout.activity_main)
                    }.start()
            }
        }else{
            setTheme(R.style.Theme_AnimatedSplashScreen)
            setContentView(R.layout.activity_main)
        }

    }
}