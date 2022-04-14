package com.example.omapp.data.network

import com.example.omapp.common.DataResponse
import com.example.omapp.common.Mapper
import com.example.omapp.data.DataSource
import com.example.omapp.data.network.model.MovieListDTO
import com.example.omapp.domain.model.Movie
import retrofit2.awaitResponse

class NetworkDataSourceImpl(
    private val service: MovieAPI,
    private val mapper: Mapper<List<Movie>, MovieListDTO>
): DataSource {
    companion object {
        const val NETWORK_DATA_SOURCE_NAME = "LocalDataSourceImpl"
    }

    override suspend fun getMovieList(page: Int): DataResponse<List<Movie>> {
        return runCatching {
            val fromValue = page * 5
            service.getMovies(fromValue).awaitResponse()
        }.fold(
            onSuccess = {
                var resultList = listOf<Movie>()
                it.body()?.apply { resultList = mapper.mapToDomainModel(this) }
                DataResponse.Success(resultList)
            },
            onFailure = { DataResponse.Failure(it) }
        )
    }
}
