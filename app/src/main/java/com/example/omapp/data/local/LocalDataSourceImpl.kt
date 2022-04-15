package com.example.omapp.data.local

import androidx.annotation.VisibleForTesting
import com.example.omapp.common.DataResponse
import com.example.omapp.data.LocalDataSource
import com.example.omapp.data.TimedCache
import com.example.omapp.domain.model.Movie


const val CACHE_LIFE_TIME = 30000
const val MOVIE_LIST_CACHE = "MovieListCache"
class LocalDataSourceImpl(
    private val timedCache: TimedCache
) : LocalDataSource {


    override suspend fun storeMovies(movies: List<Movie>): Boolean {
        timedCache[MOVIE_LIST_CACHE] = movies
        return true
    }

    override suspend fun getMovieList(): DataResponse<List<Movie>> {
        timedCache.get<List<Movie>>(MOVIE_LIST_CACHE)?.let {
            return DataResponse.Success(it)
        }
        return DataResponse.Failure
    }
}