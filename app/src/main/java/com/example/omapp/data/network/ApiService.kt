package com.example.omapp.data.network

import com.example.omapp.BASE_URL
import com.example.omapp.GET_MOVIES_PATH
import com.example.omapp.GET_MOVIE_PATH
import com.example.omapp.data.network.model.MovieDTO
import com.example.omapp.data.network.model.MovieListResponseDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_MOVIES_PATH)
    fun getMovies(@Query("from") from: Int): Call<MovieListResponseDTO>

    @GET(GET_MOVIE_PATH)
    fun getMovieDetail(@Query("external_id") id: String): Call<MovieDTO>

    companion object {
        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}