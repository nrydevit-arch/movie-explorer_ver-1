package com.stars.movieexplorer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.room.Room
import com.stars.movieexplorer.data.local.MovieDatabase
import com.stars.movieexplorer.data.local.dao.MovieDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_explorer.db"
        ).build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao =
        database.movieDao()
}