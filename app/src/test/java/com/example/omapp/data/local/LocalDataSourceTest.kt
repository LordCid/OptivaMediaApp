package com.example.omapp.data.local

import com.example.omapp.data.LocalDataSource
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.data.TimedCache
import com.example.omapp.domain.model.Movie
import com.example.omapp.movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Ignore

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
//            every { timedCache[any()] = any() } returns {}

            val actual = sut.storeMovies(input)

            verify { timedCache[MOVIE_LIST_CACHE] = input }
            assertTrue(actual)
        }
    }

    @Ignore
    @Test
    fun `GIVEN movies in memory cache valid WHEN get movies THEN return movies`() {
        runBlocking {
            val expected = listOf(movie)
//            every { timedCache.currentDate } returns Calendar.getInstance().apply { timeInMillis =  }
            every<List<Movie>?> { timedCache[MOVIE_LIST_CACHE] } returns expected

            val actual = sut.getMovieList()

            assertEquals(expected, actual)
        }
    }
}