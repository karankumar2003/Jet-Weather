package com.example.jetweather.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.pref.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(private val pref: UserPref) : ViewModel() {


    private val _defaultCity = MutableStateFlow<String>("")
    val defaultCity = _defaultCity.asStateFlow()

    private val _defaultUnit = MutableStateFlow<String>("")
    val defaultUnit = _defaultUnit.asStateFlow()

    private val _isDefaultThemeDynamic = MutableStateFlow(false)
    val isDefaultThemeDynamic = _isDefaultThemeDynamic.asStateFlow()

    init {
        viewModelScope.launch {
            pref.getDefaultCity().collect { city ->
                if (city != "") {
                    _defaultCity.value = city
                } else {
                    Log.d("PreferencesViewModel", "Empty default city")
                }
            }
        }

        viewModelScope.launch {
            pref.getDefaultUnit().collect { unit ->
                if (unit != "") {
                    _defaultUnit.value = unit
                } else {
                    Log.d("PreferencesViewModel", "Empty default city")
                }
            }
        }
        viewModelScope.launch {
            pref.getIsDynamicTheme().collect { boolean ->
                _isDefaultThemeDynamic.value = boolean
            }
        }
    }


    fun saveDefaultCity(city: String) {
        viewModelScope.launch {
            pref.saveDefaultCity(city)
        }
    }

    fun saveDynamicTheme(boolean: Boolean) {
        viewModelScope.launch {
            pref.saveDynamicTheme(boolean)
        }
    }


    fun saveDefaultUnit(unit: String) {
        viewModelScope.launch {
            pref.saveDefaultUnit(unit)
        }
    }


}