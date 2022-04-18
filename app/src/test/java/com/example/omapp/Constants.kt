package com.example.omapp

import com.example.omapp.data.network.model.MovieDTO
import com.example.omapp.data.network.model.MovieListDTO
import com.example.omapp.domain.model.Movie

val movieListDTO = MovieListDTO(
    response = listOf(
        MovieDTO(
            id = 12345,
            name = "movie name",
            description = "description",
            definition = "HD",
            year = 2017,
            duration = 7691000,
            attachments = emptyList(),
            genreEntityList = emptyList()
        )
    )
)

val movie = Movie(
    id = 12345,
    name = "movie name",
    description = "description",
    definition = "HD",
    year = 2017,
    duration = 7691000
)

val otherMovieListDTO = MovieListDTO(
    response = listOf(
        MovieDTO(
            id = 786543,
            name = "Other movie name",
            description = "other description",
            definition = "HD",
            year = 2019,
            duration = 7691000,
            attachments = emptyList(),
            genreEntityList = emptyList()
        )
    )
)

val otherMovie = Movie(
    id = 786543,
    name = "Other movie name",
    description = "other description",
    definition = "HD",
    year = 2019,
    duration = 7691000
)