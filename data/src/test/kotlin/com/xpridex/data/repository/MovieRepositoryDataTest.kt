package com.xpridex.data.repository

import com.google.common.truth.Truth
import com.xpridex.data.datasource.MovieDataSource
import com.xpridex.data.model.PagedResponseModel
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
class MovieRepositoryDataTest {
    @MockK
    private lateinit var mockDataSource: MovieDataSource

    @InjectMockKs
    private lateinit var sutRepositoryData: MovieRepositoryData

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Given the DataSource respond successful data When getMovies is called Then return the PagedResponseModel`() {
        runBlockingTest {
            coEvery {
                mockDataSource.getMovies(
                    1
                )
            } returns PagedResponseModel(page = 1)

            val response = sutRepositoryData.getMovies(1)

            with(response) {
                Truth.assertThat(page).isEqualTo(1)
            }
            coVerify { mockDataSource.getMovies(1) }
            confirmVerified(mockDataSource)
        }
    }

    @Test(expected = Exception::class)
    fun `Given the DataSource respond with an exception When getMovies is called Then return exception`() {
        runBlockingTest {
            coEvery { mockDataSource.getMovies(1) } throws Exception()

            sutRepositoryData.getMovies(1)
        }
    }
}