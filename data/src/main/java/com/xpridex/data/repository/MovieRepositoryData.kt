package com.xpridex.data.repository

import com.xpridex.data.datasource.MovieDataSource
import com.xpridex.data.model.toEntityList
import com.xpridex.domain.entity.MovieEntity
import com.xpridex.domain.entity.PagedResponseEntity
import com.xpridex.domain.repository.MovieRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class MovieRepositoryData
@Inject
constructor(private val dataSource: MovieDataSource) : MovieRepository {
    override suspend fun getMovies(page: Int): PagedResponseEntity<MovieEntity> {
        val moviesModel = dataSource.getMovies(page)

        return PagedResponseEntity(
            page = moviesModel.page,
            totalPages = moviesModel.totalPages,
            totalResults = moviesModel.totalResults,
            results = toEntityList(moviesModel.results)
        )
    }
}