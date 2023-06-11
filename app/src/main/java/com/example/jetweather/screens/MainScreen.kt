package com.example.jetweather.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.components.HumidityCard
import com.example.jetweather.components.TodayCard
import com.example.jetweather.components.WeatherAppBar
import com.example.jetweather.components.WeeklyDetail
import com.example.jetweather.data.DataOrException
import com.example.jetweather.model.Weather
import com.example.jetweather.util.timeFormat
import com.example.jetweather.viewModel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel, city: String?) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)
    ) {
        value = mainViewModel.getWeather(city!!)
    }.value

    if (weatherData.isLoading == true) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))

        }
        Log.d("Haha", "loading: ${weatherData.data.toString()}")
    } else {
        Log.d("Haha", "MainScreen: ${weatherData.data.toString()}")

        Scaffold(
            topBar = {
                WeatherAppBar(
                    title = "${weatherData.data!!.city.name}, ${weatherData.data!!.city.country}",
                    isMainScreen = true,
                    navController = navController,
                    onSearchClicked = {
                        navController.navigate(WeatherScreens.SearchScreen.name)
                    }
                )
            }
        ) {
            MainScreenContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                weatherData=weatherData
            )
        }

    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    weatherData:DataOrException<Weather, Boolean, Exception>
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TodayCard(
            elevation = 4.dp,
            weatherData = weatherData,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(150.dp)
        )

        HumidityCard(
            elevation = 4.dp,
            wind = weatherData.data!!.list[0].wind.speed.toString(),
            humidity = weatherData.data!!.list[0].main.humidity.toString(),
            pressure = weatherData.data!!.list[0].main.pressure.toString(),
            visibility=weatherData.data!!.list[0].visibility.toString(),
            sunrise= timeFormat( weatherData.data!!.city.sunrise),
            sunset= timeFormat( weatherData.data!!.city.sunset),
            modifier = Modifier
                .padding(10.dp)
        )
        WeeklyDetail(weatherData,modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
        )

    }

}

@Preview
@Composable
fun MainScreenPreview() {
}