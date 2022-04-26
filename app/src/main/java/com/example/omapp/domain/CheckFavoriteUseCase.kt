package com.example.omapp.domain

interface CheckFavoriteUseCase {
    suspend operator fun invoke(id: Long) : Boolean
}

class CheckFavoriteUseCaseImpl(
    private val repository: Repository
) : CheckFavoriteUseCase {
    override suspend fun invoke(id: Long) = repository.checkIfFavorite(id)
}