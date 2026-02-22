package com.vald3nir.toolkit.themas.presentation

import com.vald3nir.toolkit.themas.domain.AppThemeDTO

sealed interface SelectThemeUiState {
    data object Loading : SelectThemeUiState
    data class Success(val settings: AppThemeDTO) : SelectThemeUiState
}