package com.example.omapp.domain

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie

interface Repository {
    suspend fun getMovieList(index: Int) : DataResponse<List<Movie>>
}