package com.example.animatedsplashscreen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    companion object{
        const val splashFadeDurationMillis = 500
    }

    @ExperimentalAnimationApi
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
                    setContent{
                       StartScreen()
                    }
                }.start()
        }
    }
}




@ExperimentalAnimationApi
@Preview
@Composable
fun StartScreen() {
    var visible by remember { mutableStateOf(false) }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.blue))
                    .fillMaxSize(),
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = {
                            // Slide in from top
                            -it
                        },
                        animationSpec = tween(
                                durationMillis = MainActivity.splashFadeDurationMillis,
                        easing = LinearEasing
                    )
                    ),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            .background(colorResource(id = R.color.blue))
                            .fillMaxSize()
                    ) {
                        Text(
                            stringResource(id = R.string.start_screen_title),
                            fontSize = 36.sp,
                            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.start_content_title_margin_bottom)),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .height(dimensionResource(R.dimen.start_content_size))
                                .width(dimensionResource(R.dimen.start_content_size))
                                .clip(
                                    RoundedCornerShape(8.dp)
                                )
                                .background(color = Color.White)
                        )
                    }
                }
            }
            LaunchedEffect(true) {
                visible = true
            }
        }
    )
}
