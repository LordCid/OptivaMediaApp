package com.example.omapp.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SetFavoriteMovieUseCaseTest {

    private lateinit var sut : SetFavoriteMovieUseCase
    private val repository = mockk<Repository>()

    @Before
    fun setUp() {
        sut = SetFavoriteMovieUseCaseImpl(repository)
    }

    @Test
    fun `GIVEN repository set Favorite succed WHEN invoked THEN true is returned`() {
        runBlocking {
            val id = 1234L
            val isFavorite = true
            coEvery { repository.setFavorite(any(), any()) } returns true

            val actual = sut.invoke(id, isFavorite)

            coVerify { repository.setFavorite(id, isFavorite) }
            assertTrue(actual)
        }
    }

    @Test
    fun `GIVEN repository set Favorite fails WHEN invoked THEN false is returned`() {
        runBlocking {
            val id = 4564L
            val isFavorite = false
            coEvery { repository.setFavorite(any(), any()) } returns false

            val actual = sut.invoke(id, isFavorite)

            coVerify { repository.setFavorite(id, isFavorite) }
            assertFalse(actual)
        }
    }
}