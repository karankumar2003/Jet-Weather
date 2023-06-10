package com.example.jetweather.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter

@Composable
fun WeatherImage(imageUrl:String,modifier:Modifier= Modifier) {

    Image(painter= rememberImagePainter(imageUrl),contentDescription = "Weather Image",modifier=modifier)
}