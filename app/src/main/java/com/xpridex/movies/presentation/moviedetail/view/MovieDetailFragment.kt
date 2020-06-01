package com.xpridex.movies.presentation.moviedetail.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.xpridex.movies.R
import com.xpridex.movies.databinding.MovieDetailFragmentBinding
import com.xpridex.movies.presentation.common.BaseFragment
import com.xpridex.movies.presentation.moviedetail.viewmodel.MovieDetailViewModel
import com.xpridex.movies.presentation.movielist.model.Movie

class MovieDetailFragment : BaseFragment() {
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var dataBinder: MovieDetailFragmentBinding

    override fun getLayoutResId() = R.layout.movie_detail_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinder = MovieDetailFragmentBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movie = arguments?.getParcelable<Movie>("movie")
        movie?.let { viewModel.setMovie(it) }
        bindViewModel()
    }

    private fun bindViewModel() {
        dataBinder.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MovieDetailFragment.viewLifecycleOwner
        }
    }
}