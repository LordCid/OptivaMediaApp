package com.example.omapp.data.network.model

import com.google.gson.annotations.SerializedName

data class GenereEntityDTO (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
)
