package com.vald3nir.shoppinglist.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.themas.repository.ThemaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(private val themaRepository: ThemaRepository) : ViewModel() {

    val uiState: StateFlow<MainUiState> = themaRepository.appThemaFlow.map {
        MainUiState.Success(it)
    }.onEach {

    }.stateIn(
        scope = viewModelScope,
        initialValue = MainUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}