package com.stars.movieexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stars.movieexplorer.data.local.entity.MovieDetailsEntity
import com.stars.movieexplorer.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

// data/local/dao/MovieDao.kt
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY voteAverage DESC")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY title")
    fun getFavorites(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_details WHERE id = :id")
    fun getMovieDetails(id: Int): Flow<MovieDetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(details: MovieDetailsEntity)

    @Query("UPDATE movies SET isFavorite = NOT isFavorite WHERE id = :movieId")
    suspend fun toggleFavorite(movieId: Int)

    @Query("UPDATE movie_details SET isFavorite = NOT isFavorite WHERE id = :movieId")
    suspend fun toggleFavoriteDetails(movieId: Int)
}