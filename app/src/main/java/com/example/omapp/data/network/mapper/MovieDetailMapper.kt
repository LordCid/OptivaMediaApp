package com.example.omapp.data.network.mapper

import com.example.omapp.IMAGES_BASE_PATH
import com.example.omapp.common.Mapper
import com.example.omapp.data.network.model.MovieDTO
import com.example.omapp.data.network.model.MovieListDTO
import com.example.omapp.domain.model.Movie

class MovieDetailMapper : Mapper<Movie, MovieDTO> {
    companion object{
        const val MOVIE_DETAIL_MAPPER_NAME = "MovieDetailtMapper"
    }

    override fun map(input: MovieDTO) = Movie(
        id = input.id,
        externalId = input.externalId,
        name = input.name,
        description = input.description,
        definition = input.definition,
        year = input.year,
        duration = input.duration,
        imagesURL = input.attachments.map { attachment -> "$IMAGES_BASE_PATH${attachment.value}" },
        genres = input.genreEntityList.map { genre -> genre.name },
        isFavorite = false
    )

}