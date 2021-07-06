package com.example.animatedsplashscreen

import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object{
        const val splashFadeDurationMillis = 500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val transitionDrawable = window.decorView.background as TransitionDrawable
        transitionDrawable.startTransition(splashFadeDurationMillis)
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(splashFadeDurationMillis.toLong())
            setContentView(R.layout.activity_main)
        }
    }
}