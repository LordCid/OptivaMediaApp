package com.example.omapp.di

import com.example.omapp.common.Mapper
import com.example.omapp.data.RepositoryImpl
import com.example.omapp.data.local.LocalDataSource
import com.example.omapp.data.local.LocalDataSourceImpl
import com.example.omapp.data.local.mapper.LocalModelToMovieMapper
import com.example.omapp.data.local.mapper.LocalModelToMovieMapper.Companion.LOCAL_TO_MOVIE_MAPPER_NAME
import com.example.omapp.data.local.mapper.MovieToLocalModelMapper
import com.example.omapp.data.local.mapper.MovieToLocalModelMapper.Companion.MOVIE_TO_LOCAL_MAPPER_NAME
import com.example.omapp.data.local.room.MovieRoomModel
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.data.network.NetworkDataSourceImpl
import com.example.omapp.data.network.mapper.MovieListMapper
import com.example.omapp.data.network.mapper.MovieListMapper.Companion.MOVIE_LIST_MAPPER_NAME
import com.example.omapp.data.network.model.MovieListDTO
import com.example.omapp.domain.GetMoviesUseCase
import com.example.omapp.domain.GetMoviesUseCaseImpl
import com.example.omapp.domain.Repository
import com.example.omapp.domain.model.Movie
import com.example.omapp.presentation.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieListModule = module {

    // region Mappers
    single<Mapper<List<Movie>, MovieListDTO>>(named(MOVIE_LIST_MAPPER_NAME)) { MovieListMapper() }
    single<Mapper<MovieRoomModel, Movie>>(named(MOVIE_TO_LOCAL_MAPPER_NAME)) { MovieToLocalModelMapper() }
    single<Mapper<Movie, MovieRoomModel>>(named(LOCAL_TO_MOVIE_MAPPER_NAME)) { LocalModelToMovieMapper() }
    // endregion

    // region DataSource
    single<NetworkDataSource> {
        NetworkDataSourceImpl(
            service = get(),
            mapper = get(named(MOVIE_LIST_MAPPER_NAME))
        )
    }
    single<LocalDataSource> {
        LocalDataSourceImpl(
            dao = get(),
            movieToLocalMapper = get(named(MOVIE_TO_LOCAL_MAPPER_NAME)),
            localToMovieMapper = get(named(LOCAL_TO_MOVIE_MAPPER_NAME))
        )
    }
    // endregion

    // region Repository
    single<Repository> { RepositoryImpl(networkDataSource = get(), localDataSource = get()) }
    // endregion

    // region UseCase
//    single { MovieListPagingHelper(repository = get()) }
    single<GetMoviesUseCase> { GetMoviesUseCaseImpl(repository = get()) }
//    // endregion

    // region VieModel
    viewModel { MovieListViewModel(getMoviesUseCase = get(), ioDispatcher = get()) }
    // endregion
}