package com.example.omapp.data.local

import com.example.omapp.data.DataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LocalDataSourceTest {

    private lateinit var sut: DataSource

    @Before
    fun setUp() {
        sut = LocalDataSourceImpl()
    }

    @Test
    fun `GIVEN movies in memory cache valid WHEN get movies THEN return movies`() {
        runBlocking {
            val index = 0

            val actual = sut.getMovieList(0)
        }
    }
}