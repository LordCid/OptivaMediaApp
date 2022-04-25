package com.example.omapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omapp.ERROR_GENERIC_MESSAGE
import com.example.omapp.common.DataResponse
import com.example.omapp.domain.GetMovieDetailUseCase
import com.example.omapp.domain.model.Movie
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


sealed class MovieDetailViewState {
    object Loading : MovieDetailViewState()
    class ShowMovies(val data: Movie) : MovieDetailViewState()
    class Error(val message: String) : MovieDetailViewState()
}

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<MovieDetailViewState>()
    val viewState : LiveData<MovieDetailViewState>
        get() = mutableViewState

    fun getMovieDetail(id: String){
        mutableViewState.value = MovieDetailViewState.Loading
        viewModelScope.launch {
            getMovieDetailUseCase(id)
                .catch {
                    mutableViewState.postValue(
                        MovieDetailViewState.Error(it.message ?: ERROR_GENERIC_MESSAGE)
                    )
                }
                .collect { evaluateResponse(it) }

        }
    }

    private fun evaluateResponse(it: DataResponse<Movie>) {
        when (it) {
            is DataResponse.Failure ->
                mutableViewState.postValue(MovieDetailViewState.Error(it.message))
            is DataResponse.Success ->
                mutableViewState.postValue(MovieDetailViewState.ShowMovies(it.data))
        }
    }
}