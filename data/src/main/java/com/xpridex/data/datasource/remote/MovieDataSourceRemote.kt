package com.xpridex.data.datasource.remote

import com.xpridex.data.api.MovieApi
import com.xpridex.data.datasource.MovieDataSource
import com.xpridex.data.model.MovieModel
import com.xpridex.data.model.PagedResponseModel
import javax.inject.Inject

class MovieDataSourceRemote
@Inject
constructor(private val api: MovieApi) : MovieDataSource {

    override suspend fun getMovies(page: Int): PagedResponseModel<MovieModel> {
        return api.getMovies(page)
    }
}