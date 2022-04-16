package com.example.omapp.data

import com.example.omapp.domain.model.Movie
import com.example.omapp.movie
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import java.util.*

class TimedCacheTest {

    private lateinit var sut: TimedCache

    @Before
    fun setUp() {
        sut = TimedCache()
    }

    @Test
    fun `GIVEN no cached movies WHEN set movies THEN movie list is stored in cache`() {
        val expected = mutableListOf(movie)

        sut.set(expected)

        assertEquals(expected, sut.cache.first)
    }

    @Test
    fun `GIVEN 15 cached movies WHEN set movies THEN old list is replaced by new list`() {
        val expected = mutableListOf(movie)
        givenPreviousCache(cache15Elements)

        sut.set(expected)

        assertEquals(expected, sut.cache.first)
    }

    @Test
    fun `GIVEN valid cache WHEN  get THEN return movie list`() {
        val expected = mutableListOf(movie)
        givenPreviousCache(expected)
        givenInstantWhenValidCache()

        val actual = sut.get()

        assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN invalid cache WHEN  get THEN return empty movie list`() {
        val expected = emptyList<Movie>()
        val cache = mutableListOf(movie)
        givenPreviousCache(cache)
        givenInstantWhenInvalidCache()

        val actual = sut.get()

        assertEquals(expected, actual)
    }

    private val cache15Elements = MutableList(15) { movie}


    private  fun givenPreviousCache(movieList: MutableList<Movie>){
        sut.currentDate = {
            Calendar.getInstance().apply {
                set(Calendar.YEAR, 2018)
                set(Calendar.MONTH, 2)
                set(Calendar.DAY_OF_MONTH, 16)
                set(Calendar.HOUR, 10)
                set(Calendar.MINUTE, 10)
                set(Calendar.SECOND, 0)
            }.time
        }
        sut.set(movieList)
    }

    private fun givenInstantWhenValidCache() {
        sut.currentDate = {
            Calendar.getInstance().apply {
                set(Calendar.YEAR, 2018)
                set(Calendar.MONTH, 2)
                set(Calendar.DAY_OF_MONTH, 16)
                set(Calendar.HOUR, 10)
                set(Calendar.MINUTE, 10)
                set(Calendar.SECOND, 15)
            }.time
        }
    }

    private fun givenInstantWhenInvalidCache() {
        sut.currentDate = {
            Calendar.getInstance().apply {
                set(Calendar.YEAR, 2018)
                set(Calendar.MONTH, 2)
                set(Calendar.DAY_OF_MONTH, 16)
                set(Calendar.HOUR, 10)
                set(Calendar.MINUTE, 35)
            }.time
        }
    }
}