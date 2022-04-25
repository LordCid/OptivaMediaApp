package com.example.omapp.domain

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.filter
import androidx.paging.flatMap
import com.example.omapp.common.DataResponse
import com.example.omapp.domain.model.Movie
import com.example.omapp.movie
import com.example.omapp.otherMovie
import io.mockk.InternalPlatformDsl.toArray
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.junit.Before

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class GetMoviesUseCaseTest {

    private lateinit var sut : GetMoviesUseCase
    private val movieListPagingSource = mockk<PagingSource<Int, Movie>>(relaxed = true)


    @Before
    fun setUp() {
        sut = GetMoviesUseCaseImpl(movieListPagingSource)
    }

    @Ignore
    @Test
    fun `GIVEN movies from repository WHEN invoke THEN return Data Response`() {
        runBlocking {
            val movies = listOf(movie)
            val pagingData = PagingData.from(listOf(movies))
            val expected = PagingSource.LoadResult.Page(data = movies, null, 2)
            coEvery { movieListPagingSource.load(any()) } returns expected

            val actual = sut.invoke().first()

            assertEquals(pagingData, actual)
        }
    }

    @Ignore
    @Test
    fun `GIVEN OTHER movies from repository WHEN invoke THEN return Data Response`() {
        runBlocking {
            val movies = listOf(movie)
            val pagingData = PagingData.from(listOf(movies))
            val expected = PagingSource.LoadResult.Page(data = movies, null, 2)
            coEvery { movieListPagingSource.load(any()) } returns expected

            val actual = sut.invoke().first()

            assertEquals(pagingData, actual)
        }
    }
}