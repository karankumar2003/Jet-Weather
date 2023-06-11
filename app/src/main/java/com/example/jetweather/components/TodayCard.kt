package com.example.jetweather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetweather.data.DataOrException
import com.example.jetweather.model.Weather


@Composable
fun TodayCard(
    modifier: Modifier = Modifier,
    elevation: Dp,
    weatherData: DataOrException<Weather, Boolean, Exception>

) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            val imageUrl =
                "https://openweathermap.org/img/wn/${weatherData.data!!.list[0].weather[0].icon}@2x.png"

            Column() {
                WeatherImage(imageUrl = imageUrl, modifier = Modifier.size(95.dp))

                Text(
                    text = weatherData.data!!.list[0].weather[0].description.replaceFirstChar {
                        it.uppercase()
                    },
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxHeight()
            ) {
                Text("Today", style = MaterialTheme.typography.titleMedium)
                Text(
                    weatherData.data!!.list[0].main.temp.toInt().toString() + "°",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 45.sp
                )

                Text(
                    text = "Feels like " + weatherData.data!!.list[0].main.feels_like.toInt() + "°",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp
                )

            }
        }
    }
}