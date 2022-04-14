package com.example.omapp.data

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("definition") val definition: String,
    @SerializedName("year") val year: Int,
    @SerializedName("duration") val duration: Long
)
