package com.example.omapp.data

import com.example.omapp.common.DataResponse
import com.example.omapp.data.local.LocalDataSource
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.domain.Repository
import com.example.omapp.domain.model.Movie
import java.util.*

const val CACHE_LIFE_TIME = 30000L
class RepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    private val lifetime: Long = CACHE_LIFE_TIME
    private var cacheDate: Date? = null

    override suspend fun getMovieList(page: Int): DataResponse<List<Movie>> {
        cacheDate?.let {
            return if (currentDate().time <= it.time) {
                localDataSource.getMovieList()
            } else {
                localDataSource.invalidate()
                getFromNetwork(page)
            }
        }
        return getFromNetwork(page)

    }

    private suspend fun getFromNetwork(page: Int): DataResponse<List<Movie>> {
        val result = networkDataSource.getMovieList(page)
        if (result is DataResponse.Success) {
            cacheDate = Date(currentDate().time + lifetime)
            localDataSource.storeMovies(result.data)
        }
        return result
    }


    override suspend fun getMovieDetail(id: String) = networkDataSource.getDetail(id)


    override var currentDate: () -> Date = { Date() }
}