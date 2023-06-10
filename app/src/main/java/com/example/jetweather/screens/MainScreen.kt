package com.example.jetweather.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetweather.data.DataOrException
import com.example.jetweather.model.Weather
import com.example.jetweather.viewModel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val weatherData
    = produceState<DataOrException<Weather,Boolean,Exception>>(
        initialValue = DataOrException(isLoading = true)
    ){
        value = mainViewModel.getWeather("Rewari")
    }.value

    Text(text = "${weatherData.data?.city}")

}