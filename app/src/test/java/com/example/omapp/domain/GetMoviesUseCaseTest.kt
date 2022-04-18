package com.example.omapp.domain

import com.example.omapp.common.DataResponse
import com.example.omapp.movie
import com.example.omapp.otherMovie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetMoviesUseCaseTest {

    private lateinit var sut : GetMoviesUseCase
    private val repository = mockk<Repository>()

    @Before
    fun setUp() {
        sut = GetMoviesUseCaseImpl(repository)
    }

    @Test
    fun `GIVEN movies from repository WHEN invoke THEN return Data Response`() {
        runBlocking {
            val page = 1
            val expected = DataResponse.Success(listOf(movie))
            coEvery { repository.getMovieList(any()) } returns expected

            val actual = sut.invoke(page)

            coVerify { repository.getMovieList(page) }
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `GIVEN OTHER movies from repository WHEN invoke THEN return Data Response`() {
        runBlocking {
            val page = 2
            val expected = DataResponse.Success(listOf(otherMovie))
            coEvery { repository.getMovieList(any()) } returns expected

            val actual = sut.invoke(page)

            coVerify { repository.getMovieList(page) }
            assertEquals(expected, actual)
        }
    }
}