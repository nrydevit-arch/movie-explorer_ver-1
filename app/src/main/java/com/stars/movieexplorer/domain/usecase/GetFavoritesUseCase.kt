package com.stars.movieexplorer.domain.usecase

import com.stars.movieexplorer.domain.model.Movie
import com.stars.movieexplorer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// domain/usecase/GetFavoritesUseCase.kt
class GetFavoritesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> = repository.getFavorites()
}