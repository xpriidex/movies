package com.xpridex.domain.usecase

import com.xpridex.domain.entity.MovieEntity
import com.xpridex.domain.entity.PagedResponseEntity
import com.xpridex.domain.repository.MovieRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun getMovies(page: Int): PagedResponseEntity<MovieEntity> =
        repository.getMovies(page)
}