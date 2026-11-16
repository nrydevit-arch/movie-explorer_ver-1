package com.stars.movieexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stars.movieexplorer.data.local.dao.MovieDao

// data/local/MovieDatabase.kt
@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}