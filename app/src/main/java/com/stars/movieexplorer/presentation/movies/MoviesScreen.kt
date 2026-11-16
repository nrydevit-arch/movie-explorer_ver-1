package com.stars.movieexplorer.presentation.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stars.movieexplorer.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Популярные фильмы") }) }
    ) { padding ->
        when {
            uiState.isLoading -> CircularProgressIndicator(Modifier.padding(padding))
            uiState.error != null -> Text(
                text = uiState.error ?: "Ошибка",
                modifier = Modifier.padding(padding)
            )
            else -> LazyColumn(contentPadding = padding) {
                items(uiState.movies, key = { it.id }) { movie ->
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

@Composable
fun MovieItem(
    movie: Movie,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w200${movie.posterPath}",
            contentDescription = null,
            modifier = Modifier.size(80.dp, 120.dp),
        )
        Column(Modifier.weight(1f).padding(start = 12.dp)) {
            Text(movie.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = movie.releaseDate.take(4),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "★ ${movie.voteAverage}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}

@Composable
fun AsyncImage(
    model: String,
    contentDescription: Nothing?,
    modifier: size,
    contentScale: ContentScale
) {
    TODO("Not yet implemented")
}