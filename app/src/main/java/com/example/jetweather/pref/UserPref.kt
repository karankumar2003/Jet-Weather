package com.example.jetweather.pref

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow


val CITY_KEY = stringPreferencesKey("city")
val THEME_KEY = booleanPreferencesKey("theme")
val UNIT_KEY = stringPreferencesKey("unit")

interface UserPref{

    fun getDefaultCity(): Flow<String>
    fun getIsDynamicTheme(): Flow<Boolean>
    fun getDefaultUnit(): Flow<String>


    suspend fun saveDefaultCity(city:String)
    suspend fun saveDynamicTheme(isDynamic:Boolean)
    suspend fun saveDefaultUnit(unit:String)

}