package com.stars.movieexplorer.domain.usecase

import com.stars.movieexplorer.domain.repository.MovieRepository
import javax.inject.Inject

// domain/usecase/ToggleFavoriteUseCase.kt
class ToggleFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) {
        repository.toggleFavorite(movieId)
    }
}