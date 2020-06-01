package com.xpridex.domain.entity

data class MovieEntity(
    val popularity: String,
    val voteCount: String,
    val posterPath: String,
    val id: Double,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val voteAverage: String,
    val overview: String,
    val releaseDate: String
)