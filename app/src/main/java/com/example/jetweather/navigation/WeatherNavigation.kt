package com.example.jetweather.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.screens.MainScreen
import com.example.jetweather.screens.SplashScreen
import com.example.jetweather.screens.WeatherScreens

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name,
        modifier = Modifier.fillMaxSize()
    ){
        composable(WeatherScreens.SplashScreen.name){
            SplashScreen(navController)
        }
        composable(WeatherScreens.MainScreen.name){
            MainScreen(navController)
        }
    }
}
