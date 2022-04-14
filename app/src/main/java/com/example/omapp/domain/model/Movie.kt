package com.example.omapp.domain.model


data class Movie(
    val id: Long,
    val name: String,
    val description: String,
    val definition: String,
    val year: Int,
    val duration: Long
)
