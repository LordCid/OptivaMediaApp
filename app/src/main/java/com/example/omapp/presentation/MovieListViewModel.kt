package com.example.omapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omapp.common.fold
import com.example.omapp.domain.GetMoviesUseCase
import com.example.omapp.domain.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class MovieListViewState {
    object Loading : MovieListViewState()
    class ShowMovies(val data: List<Movie>) : MovieListViewState()
    object Error : MovieListViewState()
}

class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val mutableViewState = MutableLiveData<MovieListViewState>()
    val viewState : LiveData<MovieListViewState>
        get() = mutableViewState

    fun getMovies(page: Int) {
        mutableViewState.value = MovieListViewState.Loading
        viewModelScope.launch {
            val results = withContext(ioDispatcher) { getMoviesUseCase(page) }
            results.fold(
                foldSuccess = { mutableViewState.postValue(MovieListViewState.ShowMovies(it)) },
                foldFailure = { mutableViewState.postValue(MovieListViewState.Error) }
            )
        }
    }
}