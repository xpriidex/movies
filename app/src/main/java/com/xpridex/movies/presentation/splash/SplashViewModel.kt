package com.xpridex.movies.presentation.splash

import com.xpridex.movies.presentation.common.BaseViewModel

class SplashViewModel : BaseViewModel<SplashViewModel.ViewState>(ViewState.Initial) {

    fun goToMovieList() {
        state = ViewState.GoToMovieList
    }

    sealed class ViewState : BaseViewState {
        object Initial : ViewState()
        object GoToMovieList : ViewState()
    }
}