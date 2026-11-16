package com.stars.movieexplorer.data.repository

import com.stars.movieexplorer.data.local.dao.MovieDao
import com.stars.movieexplorer.data.remote.api.MovieApi
import com.stars.movieexplorer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao,
    private val dispatchers: CoroutineDispatchers
) : MovieRepository {

    override fun getPopularMovies(page: Int): Flow<List<Movie>> =
        dao.getPopularMovies()
            .map { entities -> entities.map { it.toDomain() } }
            .flowOn(dispatchers.io)

    override suspend fun refreshPopularMovies(page: Int) {
        withContext(dispatchers.io) {
            val response = api.getPopularMovies(page)
            val entities = response.results.map { it.toDomain().toEntity() }
            dao.insertMovies(entities)
        }
    }

    override fun getMovieDetails(id: Int): Flow<MovieDetails?> =
        dao.getMovieDetails(id)
            .map { it?.toDomain() }
            .flowOn(dispatchers.io)

    override suspend fun toggleFavorite(movieId: Int) {
        withContext(dispatchers.io) {
            dao.toggleFavorite(movieId)
            dao.toggleFavoriteDetails(movieId)
        }
    }

    override fun getFavorites(): Flow<List<Movie>> =
        dao.getFavorites()
            .map { it.map { entity -> entity.toDomain() } }
            .flowOn(dispatchers.io)

    override suspend fun refreshMovieDetails(id: Int) {
        withContext(dispatchers.io) {
            val dto = api.getMovieDetails(id)
            val entity = dto.toEntity() // добавим маппер ниже
            dao.insertMovieDetails(entity)
        }
    }
}