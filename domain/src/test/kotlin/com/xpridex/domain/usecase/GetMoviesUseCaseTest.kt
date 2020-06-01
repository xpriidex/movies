package com.xpridex.domain.usecase

import com.xpridex.domain.entity.PagedResponseEntity
import com.xpridex.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest{
    @MockK
    private lateinit var mockRepository: MovieRepository

    @InjectMockKs
    private lateinit var sutUseCase: GetMoviesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Given the Repository respond successful data When getMovies is called Then return the PagedResponseEntity`() {
        runBlockingTest {
            coEvery {
                mockRepository.getMovies(
                    1
                )
            } returns PagedResponseEntity(page = 1)

            val response = sutUseCase.getMovies(1)

            with(response) {
                com.google.common.truth.Truth.assertThat(page).isEqualTo(1)
            }
            coVerify { mockRepository.getMovies(1) }
            confirmVerified(mockRepository)
        }
    }
}