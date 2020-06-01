package com.xpridex.movies.presentation.moviedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xpridex.movies.presentation.common.toLiveData
import com.xpridex.movies.presentation.movielist.model.Movie

class MovieDetailViewModel : ViewModel() {
    private val movieMutableLiveData = MutableLiveData<Movie>()
    val movieLiveData = movieMutableLiveData.toLiveData()

    fun setMovie(movie: Movie) {
        movieMutableLiveData.value = movie
    }
}