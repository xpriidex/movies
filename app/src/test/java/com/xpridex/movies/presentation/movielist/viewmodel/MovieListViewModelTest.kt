package com.xpridex.movies.presentation.movielist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.xpridex.domain.entity.MovieEntity
import com.xpridex.domain.entity.PagedResponseEntity
import com.xpridex.domain.usecase.GetMoviesUseCase
import com.xpridex.movies.MainCoroutineRule
import com.xpridex.movies.observeForTesting
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @MockK
    private lateinit var mockGetMoviesUseCase: GetMoviesUseCase

    @InjectMockKs
    private lateinit var sutViewModel: MovieListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Given the use case return PagedResponseEntity When fetchMovies is called Then change the live data state to MoviesSucces`() {
        sutViewModel.stateLiveData.observeForTesting { spiedObserver, viewStates ->
            val element = MovieEntity("", "", "", 2.0, false, "", "", "", "", "", "", "")
            val response = PagedResponseEntity(results = listOf(element))
            coEvery { mockGetMoviesUseCase.getMovies(1) } returns response

            sutViewModel.fetchMovies()

            verify { spiedObserver.onChanged(capture(viewStates)) }
            assertThat(viewStates[0]).isEqualTo(MovieListViewModel.ViewState.MoviesLoading)
            assertThat((viewStates[1] as MovieListViewModel.ViewState.MoviesSucces).movies).isNotEmpty()

            confirmVerified(spiedObserver)
        }
    }

    @Test
    fun `Given the use case return exception When fetchMovies is called Then change the live data state to MoviesError`() {
        sutViewModel.stateLiveData.observeForTesting { spiedObserver, viewStates ->
            coEvery { mockGetMoviesUseCase.getMovies(1) } throws Exception()

            sutViewModel.fetchMovies()

            verify { spiedObserver.onChanged(capture(viewStates)) }
            assertThat(viewStates[0]).isEqualTo(MovieListViewModel.ViewState.MoviesLoading)
            assertThat(viewStates[1]).isEqualTo(MovieListViewModel.ViewState.MoviesError)
            confirmVerified(spiedObserver)
        }
    }
}