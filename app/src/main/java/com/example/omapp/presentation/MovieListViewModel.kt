package com.example.omapp.presentation

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

sealed class MovieListViewState {
    object Loading : MovieListViewState()
    class ShowMovies(val data: PagingData<Movie>) : MovieListViewState()
    object Error : MovieListViewState()
}


class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val mutableViewState = MutableLiveData<PagingData<Movie>>()
    val viewState : LiveData<PagingData<Movie>>
        get() = mutableViewState

    fun getMovies() {
//        mutableViewState.value = MovieListViewState.Loading
        viewModelScope.launch {
            getMoviesUseCase(this)
                .catch {  }
                .collect { mutableViewState.postValue(it) }
//            val results = withContext(ioDispatcher) { getMoviesUseCase(page) }
//            results.fold(
//                foldSuccess = { mutableViewState.postValue(MovieListViewState.ShowMovies(it)) },
//                foldFailure = { mutableViewState.postValue(MovieListViewState.Error) }
//            )
        }
    }


}