package com.xpridex.data.datasource.remote

import com.google.common.truth.Truth
import com.xpridex.data.api.MovieApi
import com.xpridex.data.model.PagedResponseModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieDataSourceRemoteTest {
    @MockK
    private lateinit var mockApi: MovieApi

    @InjectMockKs
    private lateinit var sutDataSourceRemote: MovieDataSourceRemote

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Given the api respond successful data When getMovies is called Then return the PagedResponseModel`() {
        runBlockingTest {
            coEvery {
                mockApi.getMovies(
                    1
                )
            } returns PagedResponseModel(page = 1)

            val response = sutDataSourceRemote.getMovies(1)

            with(response) {
                Truth.assertThat(page).isEqualTo(1)
            }
            coVerify { mockApi.getMovies(1) }
            confirmVerified(mockApi)
        }
    }

    @Test(expected = HttpException::class)
    fun `Given the api respond with an exception When getMovies is called Then return exception`() {
        runBlockingTest {
            coEvery { mockApi.getMovies(1) } throws HttpException(
                Response.error<Any>(
                    500,
                    "{}".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

            sutDataSourceRemote.getMovies(1)
        }
    }
}