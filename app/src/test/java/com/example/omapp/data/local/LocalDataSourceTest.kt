package com.example.omapp.data.local

import com.example.omapp.common.DataResponse
import com.example.omapp.data.TimedCache
import com.example.omapp.movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class LocalDataSourceTest {

    private lateinit var sut: LocalDataSource
    private val timedCache = mockk<TimedCache>()

    @Before
    fun setUp() {
        sut = LocalDataSourceImpl(timedCache)
    }

    @Test
    fun `Given list of movies WHEN store list THEN  list is stored in cache`() {
        runBlocking {
            val input = listOf(movie)
            every { timedCache.set(any())} returns Unit

            sut.storeMovies(input)

            verify { timedCache.set(input.toMutableList()) }
        }
    }


    @Test
    fun `GIVEN movies in memory cache valid WHEN get movies THEN return movies`() {
        runBlocking {
            val movies = listOf(movie)
            val expected = DataResponse.Success(movies)
            every { timedCache.get() } returns movies

            val actual = sut.getMovieList()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `GIVEN movies in memory cache invalid WHEN get movies THEN return empty list`() {
        runBlocking {
            val expected = DataResponse.Failure
            every { timedCache.get() } returns emptyList()

            val actual = sut.getMovieList()

            assertEquals(expected, actual)
        }
    }


}