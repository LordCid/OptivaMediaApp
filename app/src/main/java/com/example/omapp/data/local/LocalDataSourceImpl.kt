package com.example.omapp.data.local

import com.example.omapp.common.DataResponse
import com.example.omapp.common.Mapper
import com.example.omapp.data.local.room.MovieDao
import com.example.omapp.data.local.room.MovieRoomModel
import com.example.omapp.domain.model.Movie


class LocalDataSourceImpl(
    private val dao: MovieDao,
    private val movieToLocalMapper: Mapper<MovieRoomModel, Movie>,
    private val localToMovieMapper: Mapper<Movie, MovieRoomModel>
) : LocalDataSource {


    override suspend fun storeMovies(movies: List<Movie>) {
        dao.insertMovies(
            movies.map { movieToLocalMapper.map(it) }
        )

    }

    override suspend fun getMovieList(): DataResponse<List<Movie>> {
        dao.getMovies().let {
            return if (it.isNotEmpty())
                DataResponse.Success(
                    it.map {roomModel -> localToMovieMapper.map(roomModel) }
                )
            else
                DataResponse.Failure
        }
    }

    override suspend fun invalidate() {
        dao.deleteMovies()
    }

}