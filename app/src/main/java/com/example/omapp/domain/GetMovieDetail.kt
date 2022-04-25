package com.example.omapp.domain

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetMovieDetail {
    suspend operator fun invoke(id: String): Flow<DataResponse<Movie>>
}

class GetMovieDetailImpl(
    private val repository: Repository
) : GetMovieDetail{
    override suspend fun invoke(id: String) = flow { emit(repository.getMovieDetail(id)) }
}