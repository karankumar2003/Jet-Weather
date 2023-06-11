package com.example.jetweather.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetweather.data.FavoriteDao
import com.example.jetweather.db.WeatherDatabase
import com.example.jetweather.network.WeatherApi
import com.example.jetweather.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesWeatherApi(): WeatherApi
    = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideFavoriteDao(database:WeatherDatabase):FavoriteDao
    = database.getFavoriteDao()

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context:Context):WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_db"
    ).build()


}
