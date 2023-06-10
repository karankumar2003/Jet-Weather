package com.example.jetweather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetweather.R

@Composable
fun HumidityCard(
    modifier: Modifier = Modifier,
    elevation: Dp,
    wind: String,
    humidity: String,
    pressure: String
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = "Wind",
                    Modifier.size(30.dp)
                )
                Text(text = wind)
                Text(text = "Wind", modifier = Modifier.alpha(0.8f),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.humidity),
                    contentDescription = "Humidity",
                    Modifier.size(30.dp)
                )
                Text(text = humidity+"%")
                Text(text = "Humidity", modifier = Modifier.alpha(0.8f),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pressure),
                    contentDescription = "Pressure",
                    Modifier.size(30.dp)
                )
                Text(text = pressure+" psi")
                Text(
                    text = "Pressure",
                    modifier = Modifier.alpha(0.8f),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}