package com.stars.movieexplorer.presentation.details

import com.stars.movieexplorer.domain.model.MovieDetails

// presentation/details/DetailsUiState.kt
data class DetailsUiState(
    val details: MovieDetails? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)