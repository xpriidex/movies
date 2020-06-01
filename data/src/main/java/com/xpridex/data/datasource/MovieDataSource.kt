package com.xpridex.data.datasource

import com.xpridex.data.model.MovieModel
import com.xpridex.data.model.PagedResponseModel

interface MovieDataSource {
    suspend fun getMovies(page: Int): PagedResponseModel<MovieModel>
}