package com.xpridex.domain.repository

import com.xpridex.domain.entity.MovieEntity
import com.xpridex.domain.entity.PagedResponseEntity

interface MovieRepository {
    suspend fun getMovies(page: Int): PagedResponseEntity<MovieEntity>
}