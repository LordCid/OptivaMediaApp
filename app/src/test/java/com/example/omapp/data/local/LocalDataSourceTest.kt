package com.example.omapp.data.local

import com.example.omapp.ERROR_DATABASE_GENERIC_MESSAGE
import com.example.omapp.common.DataResponse
import com.example.omapp.data.local.mapper.LocalModelToMovieMapper
import com.example.omapp.data.local.mapper.MovieToLocalModelMapper
import com.example.omapp.data.local.room.MovieDao
import com.example.omapp.movie
import com.example.omapp.movieRoom
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class LocalDataSourceTest {

    private lateinit var sut: LocalDataSource
    private val movieDao = mockk<MovieDao>()
    private val movieToLocalMapper = MovieToLocalModelMapper()
    private val localToMovieMapper = LocalModelToMovieMapper()

    @Before
    fun setUp() {
        sut = LocalDataSourceImpl(movieDao, movieToLocalMapper, localToMovieMapper)
    }

    @Test
    fun `Given list of movies WHEN store list THEN  list is stored in cache`() {
        runBlocking {
            val movies = listOf(movie)
            val localModelList = listOf(movieRoom)
            coEvery { movieDao.insertMovies(any())} returns emptyList()

           sut.storeMovies(movies)

            coVerify { movieDao.insertMovies(localModelList) }
        }
    }

    @Test
    fun `GIVEN movies in cache WHEN get movies THEN return movies`() {
        runBlocking {
            val movies = listOf(movie)
            val localModelList = listOf(movieRoom)
            val expected = DataResponse.Success(movies)
            coEvery { movieDao.getMovies() } returns localModelList

            val actual = sut.getMovieList()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `GIVEN no movies in cache WHEN get movies THEN return movies`() {
        runBlocking {
            val expected = DataResponse.Failure(ERROR_DATABASE_GENERIC_MESSAGE)
            coEvery { movieDao.getMovies() } returns emptyList()

            val actual = sut.getMovieList()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `GIVEN movies in cache WHEN invalidate THEN cache is deleted`() {
        runBlocking {
            coEvery { movieDao.deleteMovies() } returns Unit

            sut.invalidate()

            coVerify { movieDao.deleteMovies() }
        }
    }

}