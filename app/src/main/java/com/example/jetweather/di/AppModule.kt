package com.example.jetweather.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.jetweather.data.DatabaseDao
import com.example.jetweather.db.WeatherDatabase
import com.example.jetweather.network.WeatherApi
import com.example.jetweather.pref.UserPref
import com.example.jetweather.pref.UserPrefImpl
import com.example.jetweather.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesWeatherApi(): WeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideFavoriteDao(database: WeatherDatabase): DatabaseDao = database.getFavoriteDao()

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context :Context):DataStore<Preferences>{
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler {
                emptyPreferences()
            }, produceFile = {context.preferencesDataStoreFile("user_data")}
        )
    }

    @Provides
    @Singleton
    fun provideUserPref(dataStore:DataStore<Preferences>):UserPref = UserPrefImpl(dataStore)


}
