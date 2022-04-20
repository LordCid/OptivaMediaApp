package com.example.omapp.domain

import androidx.paging.*
import com.example.omapp.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend operator fun invoke(): Flow<PagingData<Movie>>
}

class GetMoviesUseCaseImpl(
    private val moviePagingSource: PagingSource<Int, Movie>
) : GetMoviesUseCase {
    companion object{
        private const val TRX_INIT_LOAD_SIZE = 5
        private const val TRX_PAGE_SIZE = 5
        private const val TRX_PREFETCH_DISTANCE = 1
    }

    override suspend fun invoke() : Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(
                pageSize = TRX_PAGE_SIZE, prefetchDistance = TRX_PREFETCH_DISTANCE,
                initialLoadSize = TRX_INIT_LOAD_SIZE, enablePlaceholders = true
            )
        ) {
            moviePagingSource
        }.flow
    }


}