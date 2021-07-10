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
        const val spacingAfterFadeDurationMillis = 150
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The savedInstanceState is not null on activity recreation
        // (we must show splash screen only once)
        val splashWasDisplayed = savedInstanceState != null
        if(!splashWasDisplayed){
            // Splash is not shown, we should:
            // 1 - Start fading out the logo (cross fading the empty background)
            (window.decorView.background as TransitionDrawable).startTransition(
                logoCrossFadeDurationMillis
            )
            // 2 = As we can't register a listener to be notified when the
            // transition drawable finishes, launches a coroutine that blocks for
            // the animation duration and sets the content view after finishing
            lifecycleScope.launch {
                // Time between the cross fade and start screen animation
                delay(logoCrossFadeDurationMillis.toLong() + spacingAfterFadeDurationMillis)
                window.decorView.background = AppCompatResources.getDrawable(
                    this@MainActivity,R.drawable.splash_background)
                setContentView(R.layout.activity_main)
            }
        }else{
            // Splash was shown before, no need to animate the transition.
            // 1 - Sets the window background to the background without the logo (if needed)
            window.decorView.background = AppCompatResources.getDrawable(
                this,R.drawable.splash_background)
            // 2 - Sets the content view instantly
            setContentView(R.layout.activity_main)
        }
    }

}
