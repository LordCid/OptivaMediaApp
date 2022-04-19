package com.example.omapp.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.omapp.domain.model.Movie
import java.util.ArrayList

class MoviePaging(
    private val id: String,
    private val onLoadInitialListener: () -> Unit
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("Not yet implemented")
    }


//    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//        val currentPage = params.key ?: 1
//        return when (val result = getTransactionByType(type, currentPage)) {
//            is DataResponse.Success -> {
//                if (currentPage == 1) onLoadInitialListener()
//                if (result.data.transactions.isEmpty()) {
//                    LoadResult.Page(ArrayList<TransactionItemDomainModel>(), params.key, params.key)
//                } else {
//                    val nextPageNumber =
//                        if (result.data.transactions.size == params.loadSize) currentPage + 1 else null
//                    LoadResult.Page(data = result.data.transactions, null, nextPageNumber)
//                }
//            }
//            is DataResponse.Failure -> LoadResult.Error(result.error)
//        }
//    }
//

}


