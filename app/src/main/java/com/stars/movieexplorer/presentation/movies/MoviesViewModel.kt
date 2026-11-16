package com.stars.movieexplorer.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stars.movieexplorer.domain.usecase.GetPopularMoviesUseCase
import com.stars.movieexplorer.domain.usecase.RefreshPopularMoviesUseCase
import com.stars.movieexplorer.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMoviesUseCase,
    private val refreshPopularMovies: RefreshPopularMoviesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesUiState(isLoading = true))
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            getPopularMovies()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { movies ->
                    _uiState.update {
                        it.copy(
                            movies = movies,
                            isLoading = false,
                            isRefreshing = false,
                            error = null
                        )
                    }
                }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            try {
                refreshPopularMovies()
            } catch (e: Exception) {
                _uiState.update { it.copy(isRefreshing = false, error = e.message) }
            }
        }
    }

    fun onFavoriteClick(movieId: Int) {
        viewModelScope.launch {
            toggleFavorite(movieId)
        }
    }
}