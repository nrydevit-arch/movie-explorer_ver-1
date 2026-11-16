package com.stars.movieexplorer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// data/local/entity/MovieEntity.kt
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val isFavorite: Boolean = false,
    val cachedAt: Long = System.currentTimeMillis(),
    val backdropPath: String?,
    val runtime: Int?,
    val genres: String
)

// data/local/entity/MovieDetailsEntity.kt
@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val runtime: Int?,
    val genres: String, // храним как "Action,Drama,Sci-Fi"
    val isFavorite: Boolean = false
)