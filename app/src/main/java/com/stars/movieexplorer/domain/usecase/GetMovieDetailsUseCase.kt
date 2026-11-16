package com.stars.movieexplorer.domain.usecase

import com.stars.movieexplorer.domain.model.MovieDetails
import com.stars.movieexplorer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<MovieDetails?> =
        repository.getMovieDetails(movieId)
}