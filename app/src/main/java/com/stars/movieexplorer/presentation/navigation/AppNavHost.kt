package com.stars.movieexplorer.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stars.movieexplorer.presentation.details.DetailsScreen
import com.stars.movieexplorer.presentation.favorites.FavoritesScreen
import com.stars.movieexplorer.presentation.movies.MoviesScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == Screen.Movies.route,
                    onClick = { navController.navigate(Screen.Movies.route) },
                    icon = { Icon(Icons.Default.Movie, null) },
                    label = { Text("Фильмы") }
                )
                NavigationBarItem(
                    selected = currentRoute == Screen.Favorites.route,
                    onClick = { navController.navigate(Screen.Favorites.route) },
                    icon = { Icon(Icons.Default.Favorite, null) },
                    label = { Text("Избранное") }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movies.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Movies.route) {
                MoviesScreen(
                    onMovieClick = { movieId ->
                        navController.navigate(Screen.Details.createRoute(movieId))
                    }
                )
            }
            composable(
                route = Screen.Details.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                DetailsScreen(
                    movieId = movieId,
                    onBack = { navController.popBackStack() }
                )
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(onMovieClick = { movieId ->
                    navController.navigate(Screen.Details.createRoute(movieId))
                })
            }
        }
    }
}