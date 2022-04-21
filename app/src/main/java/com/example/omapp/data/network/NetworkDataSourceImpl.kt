package com.example.omapp.data.network

import com.example.omapp.common.DataResponse
import com.example.omapp.common.Mapper
import com.example.omapp.data.network.model.MovieListDTO
import com.example.omapp.domain.model.Movie
import retrofit2.awaitResponse

class NetworkDataSourceImpl(
    private val service: ApiService,
    private val mapper: Mapper<List<Movie>, MovieListDTO>,
): NetworkDataSource {

    override suspend fun getMovieList(page: Int): DataResponse<List<Movie>> {
        return runCatching {
            val fromValue = page * 5
            service.getMovies(fromValue).awaitResponse()
        }.fold(
            onSuccess = {
                var resultList = listOf<Movie>()
                it.body()?.apply { resultList = mapper.map(this) }
                DataResponse.Success(resultList)
            },
            onFailure = { DataResponse.Failure(it.message ?: "") }
        )
    }

    override suspend fun getDetail(id: String): DataResponse<Movie> {
        TODO("Not yet implemented")
    }
}
