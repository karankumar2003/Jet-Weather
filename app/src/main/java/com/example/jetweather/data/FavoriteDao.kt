package com.example.jetweather.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.jetweather.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("Select * from favorite_table")
    fun getAllFavorites() : Flow<List<Favorite>>

    @Query("Select * from favorite_table where city = :city")
    suspend fun getFavoriteById(city:String):Favorite

    @Upsert()
    suspend fun insertFavorite(favoriteItem:Favorite)

    @Delete
    suspend fun deleteFavorite(favoriteItem: Favorite)

    @Query("Delete from favorite_table")
    suspend fun deleteAllFavorites()

}