package com.example.omapp.data

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("response") val response : List<MovieDTO>
)
