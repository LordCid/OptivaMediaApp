package com.example.omapp.data

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie

interface LocalDataSource {
    suspend fun storeMovies(movies: List<Movie>): Boolean
    suspend fun getMovieList(): DataResponse<List<Movie>>
}