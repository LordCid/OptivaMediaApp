package com.example.omapp.data.network

import com.example.omapp.MOVIE_COUNT
import com.example.omapp.data.network.model.MovieListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    companion object{
        const val PATH = "GetUnifiedList?client=json&statuses=published&definitions=SD;HD;4K&external_category_id=SED_3880&filter_empty_categories=true&count=$MOVIE_COUNT"
    }

    @GET(PATH)
    suspend fun getMovies(@Query("from") from: Int): Call<MovieListDTO>
}