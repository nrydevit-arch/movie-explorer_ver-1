package com.stars.movieexplorer.presentation.navigation

sealed class Screen(val route: String) {
    data object Movies : Screen("movies")
    data object Details : Screen("details/{movieId}") {
        fun createRoute(movieId: Int) = "details/$movieId"
    }
    data object Favorites : Screen("favorites")
}