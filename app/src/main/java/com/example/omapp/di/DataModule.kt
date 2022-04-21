package com.example.omapp.di

import com.example.omapp.data.RepositoryImpl
import com.example.omapp.data.local.LocalDataSource
import com.example.omapp.data.local.LocalDataSourceImpl
import com.example.omapp.data.local.mapper.LocalModelToMovieMapper
import com.example.omapp.data.local.mapper.LocalModelToMovieMapper.Companion.LOCAL_TO_MOVIE_MAPPER_NAME
import com.example.omapp.data.local.mapper.MovieToLocalModelMapper
import com.example.omapp.data.local.mapper.MovieToLocalModelMapper.Companion.MOVIE_TO_LOCAL_MAPPER_NAME
import com.example.omapp.data.network.NetworkDataSource
import com.example.omapp.data.network.NetworkDataSourceImpl
import com.example.omapp.data.network.mapper.MovieDetailMapper
import com.example.omapp.data.network.mapper.MovieDetailMapper.Companion.MOVIE_DETAIL_MAPPER_NAME
import com.example.omapp.data.network.mapper.MovieListMapper
import com.example.omapp.data.network.mapper.MovieListMapper.Companion.MOVIE_LIST_MAPPER_NAME
import com.example.omapp.domain.Repository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single<NetworkDataSource> {
        NetworkDataSourceImpl(
            service = get(),
            mapperList = get(named(MOVIE_LIST_MAPPER_NAME)),
            mapperDetail = get(named(MOVIE_DETAIL_MAPPER_NAME))
        )
    }
    single<LocalDataSource> {
        LocalDataSourceImpl(
            dao = get(),
            movieToLocalMapper = get(named(MOVIE_TO_LOCAL_MAPPER_NAME)),
            localToMovieMapper = get(named(LOCAL_TO_MOVIE_MAPPER_NAME))
        )
    }

    single<Repository> { RepositoryImpl(networkDataSource = get(), localDataSource = get()) }
}