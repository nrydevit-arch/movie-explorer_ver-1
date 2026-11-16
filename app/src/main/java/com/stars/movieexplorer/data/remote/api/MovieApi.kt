package com.stars.movieexplorer.data.remote.api

import com.stars.movieexplorer.data.remote.dto.MovieDetailsDto
import com.stars.movieexplorer.data.remote.dto.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// data/remote/api/MovieApi.kt
interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "ru-RU"
    ): PopularMoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("language") language: String = "ru-RU"
    ): MovieDetailsDto
}