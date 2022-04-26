package com.example.omapp.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CheckFavoriteUseCaseTest {

    private lateinit var sut: CheckFavoriteUseCase
    private val repository = mockk<Repository>()

    @Before
    fun setUp() {
        sut = CheckFavoriteUseCaseImpl(repository)
    }

    @Test
    fun `GIVEN movie id repository check Favorite return true WHEN invoked THEN true is returned`() {
        runBlocking {
            val id = 1234L
            coEvery { repository.checkIfFavorite(any()) } returns true

            val actual = sut.invoke(id)

            coVerify { repository.checkIfFavorite(id) }
            assertTrue(actual)
        }
    }

    @Test
    fun `GIVEN mnovie id repository check Favorite return false WHEN invoked THEN false is returned`() {
        runBlocking {
            val id = 456L
            coEvery { repository.checkIfFavorite(any()) } returns true

            val actual = sut.invoke(id)

            coVerify { repository.checkIfFavorite(id) }
            assertTrue(actual)
        }
    }
}