package com.example.omapp.data

import com.example.omapp.common.DataResponse
import com.example.omapp.data.local.LocalDataSource
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.domain.Repository
import com.example.omapp.movie
import com.example.omapp.otherMovie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RepositoryGetMovieDetailTest {

    private lateinit var sut: Repository
    private val networkDataSource = mockk<NetworkDataSource>()
    private val localDataSource = mockk<LocalDataSource>()

    @Before
    fun setUp() {
        sut = RepositoryImpl(networkDataSource, localDataSource)
    }

    @Test
    fun `GIVEN Success response from networkData source WHEN getMovieList THEN return result`() {
        runBlocking {
            val id = "ab123"
            val expected = DataResponse.Success(movie)
            coEvery { networkDataSource.getDetail(any()) } returns expected

            val actual = sut.getMovieDetail(id)

            coVerify { networkDataSource.getDetail(id) }
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `GIVEN OTHER Success response from networkData source WHEN getMovieList THEN return result`() {
        runBlocking {
            val id = "er456"
            val expected = DataResponse.Success(otherMovie)
            coEvery { networkDataSource.getDetail(any()) } returns expected

            val actual = sut.getMovieDetail(id)

            coVerify { networkDataSource.getDetail(id) }
            assertEquals(expected, actual)
        }
    }

}