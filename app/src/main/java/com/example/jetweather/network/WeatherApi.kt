package com.example.jetweather.network

import com.example.jetweather.model.Weather
import com.example.jetweather.util.Constants.API_KEY
import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast")
    fun getWeather(
        @Query("q") query:String,
        @Query("appid") appid:String=API_KEY,
        @Query("units") units:String="metric",

    ): Weather
}