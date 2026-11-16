package com.stars.movieexplorer.domain.usecase

import com.stars.movieexplorer.domain.repository.MovieRepository
import javax.inject.Inject

class RefreshPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int = 1) {
        repository.refreshPopularMovies(page)
    }
}