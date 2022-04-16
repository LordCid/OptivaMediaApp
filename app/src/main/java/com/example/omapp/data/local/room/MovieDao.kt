package com.example.omapp.data.local.room

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies")
    fun getMovies(): List<MovieRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(autoBus: List<MovieRoomModel>)
}