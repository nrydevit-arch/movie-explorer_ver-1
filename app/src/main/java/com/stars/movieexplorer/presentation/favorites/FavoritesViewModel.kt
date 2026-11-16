package com.stars.movieexplorer.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stars.movieexplorer.domain.model.Movie
import com.stars.movieexplorer.domain.usecase.GetFavoritesUseCase
import com.stars.movieexplorer.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// presentation/favorites/FavoritesViewModel.kt
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavoritesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    val favorites: StateFlow<List<Movie>> = getFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun onFavoriteClick(movieId: Int) {
        viewModelScope.launch {
            toggleFavorite(movieId)
        }
    }
}