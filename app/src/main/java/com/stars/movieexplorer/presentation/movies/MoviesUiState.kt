package com.stars.movieexplorer.presentation.movies

import com.stars.movieexplorer.domain.model.Movie

// presentation/movies/MoviesUiState.kt
data class MoviesUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)