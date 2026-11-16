package com.stars.movieexplorer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default
    )
}