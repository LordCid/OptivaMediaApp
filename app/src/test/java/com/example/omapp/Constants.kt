package com.example.omapp

import com.example.omapp.data.local.room.MovieRoomModel
import com.example.omapp.data.network.model.AttachmentsDTO
import com.example.omapp.data.network.model.GenreEntityDTO
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
            attachments = listOf(
                AttachmentsDTO("url")
            ),
            genreEntityList =  listOf(
                GenreEntityDTO(
                    id = 456,
                    name = "Drama"
                )
            )
        )
    )
)

val movie = Movie(
    id = 12345,
    name = "movie name",
    description = "description",
    definition = "HD",
    year = 2017,
    duration = 7691000,
    imagesURL = listOf("https://smarttv.orangetv.orange.es/stv/api/rtv/v1/images/url"),
    genres = listOf("Drama"),
    isFavorite = false
)

val movieRoom = MovieRoomModel(
    id = 12345,
    name = "movie name",
    description = "description",
    definition = "HD",
    year = 2017,
    duration = 7691000,
    imagesURL = listOf("url"),
    genres = listOf("Drama"),
    isFavorite = false
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
            attachments = listOf(
                AttachmentsDTO("other url")
            ),
            genreEntityList = listOf(
                GenreEntityDTO(
                    id = 123,
                    name = "Action"
                )
            )
        )
    )
)

val otherMovie = Movie(
    id = 786543,
    name = "Other movie name",
    description = "other description",
    definition = "HD",
    year = 2019,
    duration = 7691000,
    imagesURL = listOf("https://smarttv.orangetv.orange.es/stv/api/rtv/v1/images/other url"),
    genres = listOf("Action"),
    isFavorite = false
)

val annotherMovie = Movie(
    id = 786543,
    name = "Other movie name",
    description = "other description",
    definition = "HD",
    year = 2019,
    duration = 7691000,
    imagesURL = listOf("other url"),
    genres = listOf("Action"),
    isFavorite = true
)

val annotherMovieRoom = MovieRoomModel(
    id = 786543,
    name = "Other movie name",
    description = "other description",
    definition = "HD",
    year = 2019,
    duration = 7691000,
    imagesURL = listOf("other url"),
    genres = listOf("Action"),
    isFavorite = true
)