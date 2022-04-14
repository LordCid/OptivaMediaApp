package com.example.omapp.data

import com.example.omapp.listMovieResponse
import com.google.gson.Gson
import junit.framework.Assert.assertNotNull
import org.junit.Test

class MovieListDTOTest {
    @Test
    fun `given response when parsing then dto is valid`() {
        val output = Gson().fromJson(listMovieResponse, MovieListDTO::class.java)

        assertNotNull(output)
    }
}