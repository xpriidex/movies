package com.xpridex.movies.presentation.movielist.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xpridex.movies.R
import com.xpridex.movies.presentation.common.BaseFragment
import com.xpridex.movies.presentation.common.lastCompletelyVisibleItemPosition
import com.xpridex.movies.presentation.common.toast
import com.xpridex.movies.presentation.movielist.model.Movie
import com.xpridex.movies.presentation.movielist.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject

class MovieListFragment : BaseFragment() {
    private var moviesAdapter: MoviesAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun getLayoutResId() = R.layout.movie_list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchMovies()
        setupObserver()
    }

    private fun setupObserver() {
        getViewState()
    }

    private fun getViewState() {
        viewModel.stateLiveData.observe(
            viewLifecycleOwner,
            Observer { handleViewState(it) })
    }

    private fun handleViewState(viewState: MovieListViewModel.ViewState) {
        when (viewState) {
            is MovieListViewModel.ViewState.MoviesLoading -> {
                context?.toast("MoviesLoading")
            }

            is MovieListViewModel.ViewState.MoviesSucces -> {
                showMovies(viewState.movies)
            }


            is MovieListViewModel.ViewState.MoviesMoreSucces -> {
                showMoreMovies(viewState.movies)
            }


            is MovieListViewModel.ViewState.MoviesError -> {
                context?.toast("MoviesError")
            }

            is MovieListViewModel.ViewState.GoToMovieDetail -> {
                val movie = viewState.movie
                val action =
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                        movie = movie,
                        label = movie.title
                    )

                findNavController().navigate(action)
                viewModel.navigationDone()
            }

            else -> return
        }
    }

    private fun showMovies(movies: List<Movie>) {
        initMoviesAdapter()
        with(moviesAdapter!!) {
            items = ArrayList(movies)
            notifyDataSetChanged()

        }
    }

    private fun showMoreMovies(movies: List<Movie>) {
        with(moviesAdapter!!) {
            addAll(movies)
        }
    }

    private fun initMoviesAdapter() {
        if (moviesAdapter == null) {
            moviesAdapter = MoviesAdapter(::onMovieTapped)
            setupMoviesAdapter()
        } else {
            setupMoviesAdapter()
        }
    }

    private fun setupMoviesAdapter() {
        with(rvNotifications) {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            lastCompletelyVisibleItemPosition(::manageMoviePaged)
        }
    }

    private fun manageMoviePaged() {
        viewModel.fetchMoreMovies()
    }

    private fun onMovieTapped(movie: Movie) {
        viewModel.goToMovieDetail(movie)
    }
}