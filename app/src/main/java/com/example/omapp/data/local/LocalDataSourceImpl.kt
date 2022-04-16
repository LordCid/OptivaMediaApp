package com.example.omapp.data.local

import com.example.omapp.common.DataResponse
import com.example.omapp.data.LocalDataSource
import com.example.omapp.data.TimedCache
import com.example.omapp.domain.model.Movie


const val MOVIE_LIST_CACHE = "MovieListCache"

class LocalDataSourceImpl(
    private val timedCache: TimedCache
) : LocalDataSource {


    override suspend fun storeMovies(movies: List<Movie>) {
        timedCache.set(movies.toMutableList())

    }

    override suspend fun getMovieList(): DataResponse<List<Movie>> {
        timedCache.get().let {
            return if (it.isNotEmpty())
                DataResponse.Success(it)
            else
                DataResponse.Failure
        }
    }

}