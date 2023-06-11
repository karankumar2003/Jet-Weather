package com.example.jetweather.repository

import com.example.jetweather.data.DataOrException
import com.example.jetweather.data.FavoriteDao
import com.example.jetweather.model.Favorite
import com.example.jetweather.model.Weather
import com.example.jetweather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi,private val dao : FavoriteDao){


    suspend fun getWeather(query:String):DataOrException<Weather,Boolean,Exception> {
        val response =  try{
            api.getWeather(query)

        }catch (e:Exception){
            return DataOrException(exception = e)
        }
        return DataOrException(data = response, isLoading = false)

    }

    fun getAllFavorites() = dao.getAllFavorites()
    suspend fun getFavoriteById(city:String) = dao.getFavoriteById(city)
    suspend fun upsertFavorite(favoriteItem:Favorite) = dao.upsertFavorite(favoriteItem)
    suspend fun deleteFavorite(favoriteItem: Favorite) = dao.deleteFavorite(favoriteItem)
    suspend fun deleteAllFavorites() = dao.deleteAllFavorites()


}