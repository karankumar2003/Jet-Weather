package com.example.jetweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweather.data.FavoriteDao
import com.example.jetweather.model.Favorite

@Database(entities = [Favorite::class],version=1, exportSchema = false)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun getFavoriteDao():FavoriteDao
}