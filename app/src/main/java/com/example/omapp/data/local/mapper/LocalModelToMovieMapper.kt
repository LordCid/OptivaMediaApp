package com.example.omapp.data.local.mapper

import com.example.omapp.common.Mapper
import com.example.omapp.data.local.room.MovieRoomModel
import com.example.omapp.domain.model.Movie

class LocalModelToMovieMapper: Mapper<Movie, MovieRoomModel> {
    override fun map(input: MovieRoomModel) = Movie(
        id = input.id,
        name = input.name,
        description = input.description,
        definition = input.definition,
        year = input.year,
        duration = input.duration,
        imagesURL = input.imagesURL,
        genres = input.genres,
        isFavorite = input.isFavorite
    )
}