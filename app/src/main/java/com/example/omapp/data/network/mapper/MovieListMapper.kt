package com.example.omapp.data.network.mapper

import com.example.omapp.common.Mapper
import com.example.omapp.data.network.model.MovieListDTO
import com.example.omapp.domain.model.Movie

class MovieListMapper : Mapper<List<Movie>, MovieListDTO> {
    companion object{
        const val MOVIE_LIST_MAPPER_NAME = "MovieListMapper"
    }

    override fun map(input: MovieListDTO) = input.response.map {
        Movie(
            id = it.id,
            name = it.name,
            description = it.description,
            definition = it.definition,
            year = it.year,
            duration = it.duration,
            imagesURL = it.attachments.map { attachment -> attachment.value },
            genres = it.genreEntityList.map { genre -> genre.name },
            isFavorite = false
        )
    }
}