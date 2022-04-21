package com.example.omapp.domain

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie
import java.util.*

interface Repository {
    suspend fun getMovieList(page: Int) : DataResponse<List<Movie>>
    suspend fun getMovieDetail(id: String) : DataResponse<Movie>
    var currentDate: () -> Date
}