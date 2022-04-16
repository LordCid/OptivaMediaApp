package com.example.omapp.data

import com.example.omapp.data.local.LocalDataSource
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.domain.Repository
import io.mockk.mockk
import org.junit.Before

import org.junit.Assert.*

class RepositoryTest {

    private lateinit var sut: Repository
    private val networkDataSource = mockk<NetworkDataSource>()
    private val localDataSource = mockk<LocalDataSource>()

    @Before
    fun setUp() {
        sut = RepositoryImpl(networkDataSource, localDataSource)
    }


}