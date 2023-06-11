package com.example.jetweather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetweather.data.DataOrException
import com.example.jetweather.model.Weather
import com.example.jetweather.util.dayFormat
import com.example.jetweather.util.timeFormat

@Composable
fun WeeklyDetail(
    weatherData: DataOrException<Weather, Boolean, Exception>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "This Week",
            Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clip(RoundedCornerShape(16.dp))
        ) {
            items(weatherData.data!!.list) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imageUrl =
                        "https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"

                    Text(buildAnnotatedString {
                        withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)) {
                            append(dayFormat(it.dt))
                        }
                        append(", " + timeFormat(it.dt))
                    }, fontWeight = FontWeight.Light
                    )


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        WeatherImage(imageUrl = imageUrl, modifier = Modifier.size(75.dp))
                        Text(
                            it.weather[0].description.replaceFirstChar {
                                it.uppercase()
                            },
                            modifier = Modifier,
                            fontSize = 12.sp
                        )

                    }

                    Text(buildAnnotatedString {
                        append(it.main.temp_max.toInt().toString()+"°/")
                        withStyle(SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp
                        )) {
                            append(it.main.temp_min.toInt().toString()+"°")
                        }
                    },
                    fontWeight = FontWeight.Bold, fontSize = 24.sp
                    )


                }

                Divider()

            }
        }
    }
}