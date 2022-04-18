package com.example.omapp.domain

import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie

interface GetMoviesUseCase {
    suspend operator fun invoke(page: Int): DataResponse<List<Movie>>
}

class GetMoviesUseCaseImpl(
    private val repository: Repository
) : GetMoviesUseCase {
    override suspend fun invoke(page: Int) = repository.getMovieList(page)
}