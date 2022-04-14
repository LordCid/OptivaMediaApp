package com.example.omapp.data

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie

interface DataSource {
    suspend fun getMovieList(page: Int): DataResponse<List<Movie>>
}

