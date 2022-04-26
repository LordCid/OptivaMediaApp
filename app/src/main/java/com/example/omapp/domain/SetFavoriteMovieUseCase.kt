package com.example.omapp.domain

interface SetFavoriteMovieUseCase {
    suspend operator fun invoke(id: Long, isFavorite: Boolean) : Boolean
}

class SetFavoriteMovieUseCaseImpl (
    private val repository: Repository
) : SetFavoriteMovieUseCase {
    override suspend fun invoke(id: Long, isFavorite: Boolean) = repository.setFavorite(id, isFavorite)
}