package com.stars.movieexplorer.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stars.movieexplorer.domain.usecase.GetMovieDetailsUseCase
import com.stars.movieexplorer.domain.usecase.RefreshMovieDetailsUseCase
import com.stars.movieexplorer.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val refreshMovieDetails: RefreshMovieDetailsUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        loadDetails()
    }

    private fun loadDetails() {
        viewModelScope.launch {
            // Сначала пробуем из кэша
            getMovieDetails(movieId)
                .collect { details ->
                    _uiState.update { it.copy(details = details, isLoading = details == null) }
                }
        }

        // Параллельно обновляем из сети
        viewModelScope.launch {
            try {
                refreshMovieDetails(movieId)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            toggleFavorite(movieId)
        }
    }
}