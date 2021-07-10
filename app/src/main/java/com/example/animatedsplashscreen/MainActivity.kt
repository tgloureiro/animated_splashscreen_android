package com.example.animatedsplashscreen

import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object{
        const val logoCrossFadeDurationMillis = 300
        // Flag that allows showing the splash screen only once
        var splashDisplayed = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!splashDisplayed){
            // Splash is not shown, we should:
            // 1 - Start fading out the logo (cross fading the empty background)
            (window.decorView.background as TransitionDrawable).startTransition(
                logoCrossFadeDurationMillis
            )
            // 2 = As we can't register a listener to be notified when the
            // transition drawable finishes, launch a coroutine that blocks for
            // the animation duration, flip the flag and set the content view after finishing
            lifecycleScope.launch {
                delay(logoCrossFadeDurationMillis.toLong())
                splashDisplayed = true
                setContentView(R.layout.activity_main)
            }
        }else{
            // Splash was shown before, no need to animate the transition.
            // 1 - Set the window background to the background without the logo (if needed)
            window.decorView.background = AppCompatResources.getDrawable(
                this,R.drawable.splash_background)
            // 2 - Set the content view instantly
            setContentView(R.layout.activity_main)
        }
    }
}