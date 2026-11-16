package com.stars.movieexplorer.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stars.movieexplorer.presentation.movies.MovieItem

// presentation/favorites/FavoritesScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Избранное") }) }
    ) { padding ->
        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет избранных фильмов")
            }
        } else {
            LazyColumn(contentPadding = padding) {
                items(favorites, key = { it.id }) { movie ->
                    MovieItem(
                        movie = movie,
                        onClick = { onMovieClick(movie.id) },
                        onFavoriteClick = { viewModel.onFavoriteClick(movie.id) }
                    )
                }
            }
        }
    }
}