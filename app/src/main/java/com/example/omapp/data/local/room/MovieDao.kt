package com.example.omapp.data.local.room

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies")
    suspend fun getMovies(): List<MovieRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieRoomModel>) : List<Long>

    @Query("DELETE FROM Movies")
    suspend fun deleteMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(movieFavorite: MovieFavoriteRoomModel) : Long
}