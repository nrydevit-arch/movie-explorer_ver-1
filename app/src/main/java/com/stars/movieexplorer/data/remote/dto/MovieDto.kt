package com.stars.movieexplorer.data.remote.dto

import com.squareup.moshi.Json

// data/remote/dto/MovieDto.kt
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "release_date") val releaseDate: String
)

// data/remote/dto/PopularMoviesResponse.kt
data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
    @Json(name = "total_pages") val totalPages: Int
)

// data/remote/dto/MovieDetailsDto.kt
data class MovieDetailsDto(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "release_date") val releaseDate: String,
    val runtime: Int?,
    val genres: List<GenreDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)