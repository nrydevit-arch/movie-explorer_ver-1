package com.stars.movieexplorer.domain.repository

import com.stars.movieexplorer.domain.model.Movie
import com.stars.movieexplorer.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(page: Int = 1): Flow<List<Movie>>
    suspend fun refreshPopularMovies(page: Int = 1)
    fun getMovieDetails(id: Int): Flow<MovieDetails?>
    suspend fun toggleFavorite(movieId: Int)
    fun getFavorites(): Flow<List<Movie>>
    suspend fun refreshMovieDetails(id: Int)
}