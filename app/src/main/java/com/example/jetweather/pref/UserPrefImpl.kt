package com.example.jetweather.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class UserPrefImpl(private val dataStore: DataStore<Preferences>) : UserPref {


    override fun getDefaultCity(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[CITY_KEY] ?: ""
        }
    }

    override fun getIsDynamicTheme(): Flow<Boolean> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[THEME_KEY] ?: false
        }
    }

    override fun getDefaultUnit(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[UNIT_KEY] ?: ""
        }
    }

    override suspend fun saveDefaultCity(city: String) {
        dataStore.edit {
            it[CITY_KEY] = city
        }
    }

    override suspend fun saveDynamicTheme(isDynamic: Boolean) {
        dataStore.edit {
            it[THEME_KEY] = isDynamic
        }
    }

    override suspend fun saveDefaultUnit(unit: String) {
        dataStore.edit {
            it[UNIT_KEY] = unit
        }
    }
}