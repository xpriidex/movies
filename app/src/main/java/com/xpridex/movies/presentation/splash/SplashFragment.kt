package com.xpridex.movies.presentation.splash

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xpridex.movies.R
import com.xpridex.movies.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment() {
    private val viewModel: SplashViewModel by viewModels()

    override fun getLayoutResId() = R.layout.splash_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver()
        dogAnimationListener()

    }

    private fun setupObserver() {
        getViewState()
    }

    private fun getViewState() {
        viewModel.stateLiveData.observe(
            viewLifecycleOwner,
            Observer { handleViewState(it) })
    }

    private fun handleViewState(viewState: SplashViewModel.ViewState) {
        when (viewState) {
            is SplashViewModel.ViewState.GoToMovieList -> {
                findNavController().navigate(R.id.action_splashFragment_to_movieListFragment)
            }

            else -> return
        }
    }

    private fun dogAnimationListener() {
        lavSplash.addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                viewModel.goToMovieList()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }
}