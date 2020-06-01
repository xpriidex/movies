package com.xpridex.movies.presentation.movielist.viewmodel

import androidx.lifecycle.viewModelScope
import com.xpridex.domain.usecase.GetMoviesUseCase
import com.xpridex.movies.presentation.common.BaseViewModel
import com.xpridex.movies.presentation.movielist.model.Movie
import com.xpridex.movies.presentation.movielist.model.toModelPresentationList
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    BaseViewModel<MovieListViewModel.ViewState>(ViewState.Initial) {
    private var page = 1

    fun fetchMovies() {
        state =
            ViewState.MoviesLoading
        viewModelScope.launch {
            state = try {
                val pagedResponse = getMoviesUseCase.getMovies(page)
                val results = toModelPresentationList(pagedResponse.results)
                page++
                ViewState.MoviesSucces(
                    results
                )

            } catch (ex: Exception) {
                ViewState.MoviesError
            }
        }
    }

    fun fetchMoreMovies() {
        state =
            ViewState.MoviesLoading
        viewModelScope.launch {
            state = try {
                val pagedResponse = getMoviesUseCase.getMovies(page)
                val results = toModelPresentationList(pagedResponse.results)
                page++
                ViewState.MoviesMoreSucces(
                    results
                )

            } catch (ex: Exception) {
                ViewState.MoviesError
            }
        }
    }

    fun goToMovieDetail(movie: Movie) {
        state = ViewState.GoToMovieDetail(movie)
    }

    fun navigationDone() {
        state = ViewState.NavigationDone
    }

    sealed class ViewState : BaseViewState {
        object Initial : ViewState()
        object MoviesLoading : ViewState()
        class MoviesSucces(val movies: List<Movie>) : ViewState()
        class MoviesMoreSucces(val movies: List<Movie>) : ViewState()
        object MoviesError : ViewState()
        class GoToMovieDetail(val movie: Movie) : ViewState()
        object NavigationDone : ViewState()
    }
}