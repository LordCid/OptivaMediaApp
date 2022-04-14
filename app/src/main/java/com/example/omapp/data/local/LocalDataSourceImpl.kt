package com.example.omapp.data.local

import com.example.omapp.common.DataResponse
import com.example.omapp.data.DataSource
import com.example.omapp.domain.model.Movie

class LocalDataSourceImpl : DataSource {
    companion object {
        const val LOCAL_DATA_SOURCE_NAME = "LocalDataSourceImpl"
    }

    override suspend fun getMovieList(page: Int): DataResponse<List<Movie>> {
        TODO("Not yet implemented")
    }
}