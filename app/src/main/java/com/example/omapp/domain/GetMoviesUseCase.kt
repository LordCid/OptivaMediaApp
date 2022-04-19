package com.example.omapp.domain

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie
import com.example.omapp.presentation.MovieListPagingHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend operator fun invoke(scope: CoroutineScope): Flow<PagingData<Movie>>
}

class GetMoviesUseCaseImpl(
    private val pagingHelper: MovieListPagingHelper
) : GetMoviesUseCase {
    companion object{
        private const val TRX_INIT_LOAD_SIZE = 5
        private const val TRX_PAGE_SIZE = 5
        private const val TRX_PREFETCH_DISTANCE = 2
    }

    override suspend fun invoke(scope: CoroutineScope) : Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(
                pageSize = TRX_PAGE_SIZE, prefetchDistance = TRX_PREFETCH_DISTANCE,
                initialLoadSize = TRX_INIT_LOAD_SIZE, enablePlaceholders = true
            )
        ) {
            pagingHelper
        }.flow.cachedIn(scope)
    }

//    private suspend fun data(): PagingData<Movie> {
//        val pager = Pager(
//            PagingConfig(
//                pageSize = TRX_PAGE_SIZE, prefetchDistance = TRX_PREFETCH_DISTANCE,
//                initialLoadSize = TRX_INIT_LOAD_SIZE, enablePlaceholders = true
//            )
//        ) {
//            pagingHelper
//        }.flow.cachedIn(scope)
//    }

}