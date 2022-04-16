package com.example.omapp.data

import com.example.omapp.common.DataResponse
import com.example.omapp.data.local.LocalDataSource
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.domain.Repository
import com.example.omapp.domain.model.Movie

class RepositoryImpl(
    networkDataSource: NetworkDataSource,
    localDataSource: LocalDataSource
) : Repository {
    override suspend fun getMovieList(index: Int): DataResponse<List<Movie>> {
        TODO("Not yet implemented")
    }
}