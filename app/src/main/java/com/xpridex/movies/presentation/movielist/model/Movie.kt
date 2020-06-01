package com.xpridex.movies.presentation.movielist.model

import android.os.Parcelable
import com.xpridex.domain.entity.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var isLoading: Boolean = false,
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
) : Parcelable

fun MovieEntity.toModelPresentation() = Movie(
    popularity = popularity,
    voteCount = voteCount,
    posterPath = posterPath,
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    releaseDate = releaseDate
)

fun toModelPresentationList(list: List<MovieEntity>) = list.map { it.toModelPresentation() }