package com.stars.movieexplorer.domain.usecase

import com.stars.movieexplorer.domain.model.Movie
import com.stars.movieexplorer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(page: Int = 1): Flow<List<Movie>> =
        repository.getPopularMovies(page)
}