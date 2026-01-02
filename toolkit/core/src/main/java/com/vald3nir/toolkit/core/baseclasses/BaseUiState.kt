package com.vald3nir.toolkit.core.baseclasses

sealed interface BaseUiState {
    data object EmptySate : BaseUiState
    data object ShowState : BaseUiState
    data class LoadingState(val show: Boolean = true) : BaseUiState
}