package com.example.omapp.di

import com.example.omapp.data.local.room.AppDatabase
import com.example.omapp.data.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { ApiService.create() }
    single { AppDatabase.getDatabase(androidContext()).movieDao() }
    single { Dispatchers.IO }

}