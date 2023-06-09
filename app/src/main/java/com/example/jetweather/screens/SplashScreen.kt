package com.example.jetweather.screens

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.R
import com.example.jetweather.viewModel.PreferencesViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    preferencesViewModel:PreferencesViewModel
) {

    val defaultCity = preferencesViewModel.defaultCity.collectAsState()
    Log.d("Hahah", ": ${defaultCity.value}")

    val scale = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            0.9f,
            animationSpec = tween(800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }

            )
        )
        delay(200L)
        Log.d("Hahah", "SplashScreen: ${defaultCity.value}")
        navController.navigate(WeatherScreens.MainScreen.name + "/${defaultCity.value}"){
            popUpTo(WeatherScreens.SplashScreen.name){
                inclusive = true
            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scale(scale.value),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(330.dp)
                .clip(CircleShape)
                .border(
                    2.dp, MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center

        ) {

            Icon(
                painter = painterResource(id = R.drawable.sun_cloud), contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center), tint = MaterialTheme.colorScheme.primary
            )

        }
    }
}

