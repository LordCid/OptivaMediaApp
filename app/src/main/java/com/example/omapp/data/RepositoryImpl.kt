package com.example.omapp.data

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.Repository
import com.example.omapp.domain.model.Movie

class RepositoryImpl : Repository {
    override suspend fun getMovieList(index: Int): DataResponse<List<Movie>> {
        TODO("Not yet implemented")
    }
}