package com.example.omapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.omapp.common.fold
import com.example.omapp.domain.GetMoviesUseCase
import com.example.omapp.domain.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val mutableViewState = MutableLiveData<PagingData<Movie>>()
    val viewState : LiveData<PagingData<Movie>>
        get() = mutableViewState

    suspend fun getMovies(initialLoadListener: () -> Unit, scope: CoroutineScope) {
            getMoviesUseCase(scope)
                .catch {  }
                .collect { mutableViewState.postValue(it) }
    }


}