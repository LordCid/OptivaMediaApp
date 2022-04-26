package com.example.omapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omapp.ERROR_GENERIC_MESSAGE
import com.example.omapp.common.DataResponse
import com.example.omapp.domain.GetMovieDetailUseCase
import com.example.omapp.domain.SetFavoriteMovieUseCase
import com.example.omapp.domain.model.Movie
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


sealed class MovieDetailViewState {
    object Loading : MovieDetailViewState()
    class ShowMovies(val data: Movie) : MovieDetailViewState()
    class Error(val message: String) : MovieDetailViewState()
    class FavoriteUpdate(val isFavorite: Boolean) : MovieDetailViewState()
}

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val setFavoriteMovieUseCase: SetFavoriteMovieUseCase
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

    fun setFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            when(val result = setFavoriteMovieUseCase(id, isFavorite)) {
                is DataResponse.Failure -> mutableViewState.postValue(MovieDetailViewState.Error(result.message))
                is DataResponse.Success ->  mutableViewState.postValue(MovieDetailViewState.FavoriteUpdate(result.data))
            }
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