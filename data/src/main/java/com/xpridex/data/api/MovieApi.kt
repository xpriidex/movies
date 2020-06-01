package com.xpridex.data.api


import com.xpridex.data.api.Constants.Api.MOVIES
import com.xpridex.data.model.MovieModel
import com.xpridex.data.model.PagedResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(MOVIES)
    suspend fun getMovies(@Query("page") page: Int): PagedResponseModel<MovieModel>
}