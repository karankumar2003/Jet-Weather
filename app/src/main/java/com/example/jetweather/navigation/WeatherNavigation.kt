package com.example.jetweather.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweather.screens.AboutScreen
import com.example.jetweather.screens.FavoriteScreen
import com.example.jetweather.screens.MainScreen
import com.example.jetweather.screens.SearchScreen
import com.example.jetweather.screens.SettingsScreen
import com.example.jetweather.screens.SplashScreen
import com.example.jetweather.screens.WeatherScreens
import com.example.jetweather.viewModel.MainViewModel
import com.example.jetweather.viewModel.PreferencesViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name,
        modifier = Modifier.fillMaxSize()
    ) {


        composable(WeatherScreens.SplashScreen.name) {
            val preferencesViewModel: PreferencesViewModel = hiltViewModel()
            SplashScreen(navController,preferencesViewModel)
        }




        composable(WeatherScreens.MainScreen.name + "/{city}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("city").let { city ->

                Log.d("Haha", "WeatherNavigation: $city")
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController, mainViewModel,city)

            }

        }


        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController=navController)
        }

        composable(WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController)
        }

        composable(WeatherScreens.SettingScreen.name) {
            val preferencesViewModel = hiltViewModel<PreferencesViewModel>()
            SettingsScreen(navController,preferencesViewModel)
        }



    }
}
