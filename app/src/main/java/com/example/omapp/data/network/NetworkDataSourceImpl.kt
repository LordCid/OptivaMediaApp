package com.example.omapp.data.network

import com.example.omapp.ERROR_NETWORK_GENERIC_MESSAGE
import com.example.omapp.common.DataResponse
import com.example.omapp.common.Mapper
import com.example.omapp.data.network.model.MovieDTO
import com.example.omapp.data.network.model.MovieListResponseDTO
import com.example.omapp.domain.model.Movie
import retrofit2.awaitResponse

class NetworkDataSourceImpl(
    private val service: ApiService,
    private val mapperList: Mapper<List<Movie>, MovieListResponseDTO>,
    private val mapperDetail: Mapper<Movie, MovieDTO>
): NetworkDataSource {

    override suspend fun getMovieList(page: Int): DataResponse<List<Movie>> {
        return runCatching {
            val fromValue = page * 5
            service.getMovies(fromValue).awaitResponse()
        }.fold(
            onSuccess = {
                var resultList = listOf<Movie>()
                it.body()?.apply { resultList = mapperList.map(this) }
                DataResponse.Success(resultList)
            },
            onFailure = { DataResponse.Failure(it.message ?: ERROR_NETWORK_GENERIC_MESSAGE) }
        )
    }

    override suspend fun getDetail(id: String): DataResponse<Movie> {
        return runCatching {
            service.getMovieDetail(id).awaitResponse()
        }.fold(
            onSuccess = {
                it.body()?.let { movieDTO ->
                    DataResponse.Success(mapperDetail.map(movieDTO))
                } ?: DataResponse.Failure(ERROR_NETWORK_GENERIC_MESSAGE)
            },
            onFailure = { DataResponse.Failure(it.message ?: ERROR_NETWORK_GENERIC_MESSAGE) }
        )
    }
}
