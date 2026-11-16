package com.stars.movieexplorer.data.mapper

import com.stars.movieexplorer.data.local.entity.MovieDetailsEntity
import com.stars.movieexplorer.data.local.entity.MovieEntity
import com.stars.movieexplorer.data.remote.dto.MovieDetailsDto
import com.stars.movieexplorer.data.remote.dto.MovieDto
import com.stars.movieexplorer.domain.model.Movie
import com.stars.movieexplorer.domain.model.MovieDetails

object MovieMapper {

    fun MovieDto.toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )

    fun Movie.toEntity() = MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite,
        backdropPath = null,
        runtime = null,
        genres = ""
    )

    fun MovieEntity.toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite
    )

    fun MovieDetailsDto.toEntity() = MovieDetailsEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        runtime = runtime,
        genres = genres.joinToString(",") { it.name }
    )

    fun MovieDetailsDto.toDomain() = MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        runtime = runtime,
        genres = genres.map { it.name }
    )

    fun MovieDetailsEntity.toDomain() = MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        runtime = runtime,
        genres = genres.split(",").filter { it.isNotBlank() },
        isFavorite = isFavorite
    )
}