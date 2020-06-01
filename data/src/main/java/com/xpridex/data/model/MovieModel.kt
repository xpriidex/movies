package com.xpridex.data.model

import com.google.gson.annotations.SerializedName
import com.xpridex.data.api.Constants.Api.IMAGE_URL
import com.xpridex.domain.entity.MovieEntity

data class MovieModel(
    @SerializedName("popularity") val popularity: String,
    @SerializedName("vote_count") val voteCount: String,
    @SerializedName("poster_path") val posterPath: String,
    val id: Double,
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String
)

fun MovieModel.toEntity() = MovieEntity(
    popularity = popularity,
    voteCount = voteCount,
    posterPath = "$IMAGE_URL$posterPath",
    id = id,
    adult = adult,
    backdropPath = "$IMAGE_URL$backdropPath",
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate
)

fun toEntityList(list: List<MovieModel>) = list.map { it.toEntity() }