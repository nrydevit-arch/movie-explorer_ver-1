package com.stars.movieexplorer.domain.model

// domain/model/Movie.kt
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val isFavorite: Boolean = false
)

// domain/model/MovieDetails.kt
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val runtime: Int?,
    val genres: List<String>,
    val isFavorite: Boolean = false
)