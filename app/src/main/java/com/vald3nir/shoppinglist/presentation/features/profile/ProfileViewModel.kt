package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.repository.AppRepository
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val authenticatedUserRepository: AuthenticatedUserRepository,
    private val repository: AppRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    val userDataFlow: StateFlow<AuthenticatedUserDTO> = authenticatedUserRepository.loadAuthenticatedUser()
        .onStart {
            notifyState(BaseUiState.LoadingState())
        }.onEach {
            notifyState(BaseUiState.ShowState())
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = AuthenticatedUserDTO(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    fun logout() {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState())
                authenticatedUserRepository.logout()
                repository.deleteLists()
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }

    fun syncLists() {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState())
                repository.syncLists()
            },
            onSuccessEvent = {
                notifyState(BaseUiState.ShowState())
                notifyUiMessage("Listas sicronizadas com sucesso")
            }
        )
    }
}