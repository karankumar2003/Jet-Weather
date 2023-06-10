package com.example.jetweather.repository

import com.example.jetweather.model.Weather
import com.example.jetweather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi){

    suspend fun getWeather(query:String):Weather =
        api.getWeather(query=query)
}