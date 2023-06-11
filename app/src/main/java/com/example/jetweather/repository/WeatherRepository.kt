package com.example.jetweather.repository

import com.example.jetweather.data.DataOrException
import com.example.jetweather.model.Weather
import com.example.jetweather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi){

    suspend fun getWeather(query:String):DataOrException<Weather,Boolean,Exception> {
        val response =  try{
            api.getWeather(query)

        }catch (e:Exception){
            return DataOrException(exception = e)
        }
        return DataOrException(data = response, isLoading = false)

    }
}