package com.example.jetweather.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.model.Favorite
import com.example.jetweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val _favoriteList = MutableStateFlow<List<Favorite>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavorites().collect { list ->
                if(list.isNotEmpty()){
                    _favoriteList.value = list
                }else{
                    Log.d("FavoriteViewModel", "getAllFavorites(): Empty List of Favorites")
                }
            }
        }
    }

    fun upsertFavorite(favoriteItem:Favorite) = viewModelScope.launch { repository.upsertFavorite(favoriteItem) }
    fun deleteFavorite(favoriteItem:Favorite) = viewModelScope.launch { repository.deleteFavorite(favoriteItem) }
    fun deleteAllFavorite() = viewModelScope.launch { repository.deleteAllFavorites() }
    fun getFavoriteById(city:String) = viewModelScope.launch { repository.getFavoriteById(city) }
}