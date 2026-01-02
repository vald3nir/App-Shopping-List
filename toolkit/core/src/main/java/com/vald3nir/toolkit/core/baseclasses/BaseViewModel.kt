package com.vald3nir.toolkit.core.baseclasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.toolkit.core.services.sync.monitors.NetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel(networkMonitor: NetworkMonitor) : ViewModel() {

    val hasInternetConnection: StateFlow<Boolean> = networkMonitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    private val _uiMessage = MutableStateFlow("")
    val uiMessage: StateFlow<String> = _uiMessage.asStateFlow()
    fun notifyUiMessage(message: String) {
        _uiMessage.value = message
    }

    private val _uiState = MutableStateFlow<BaseUiState>(BaseUiState.LoadingState(false))
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    fun notifyState(state: BaseUiState) {
        _uiState.value = state
    }

    fun safeLaunch(action: suspend () -> Unit, onSuccessEvent: () -> Unit = {}, onFailureEvent: () -> Unit = {}) {
        viewModelScope.launch {
            runCatching {
                action.invoke()
            }.onFailure { error ->
                onFailureEvent()
                error.treatMessage { notifyUiMessage(it) }
            }.onSuccess {
                onSuccessEvent()
            }
        }
    }
}