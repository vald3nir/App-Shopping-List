package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.UserDataRepository
import com.vald3nir.shoppinglist.domain.ProfileScreenDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.services.sync.monitors.NetworkMonitor
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val authenticatedUserRepository: AuthenticatedUserRepository,
    private val userDataRepository: UserDataRepository,
    private val repository: ShoppingListRepository,
    networkMonitor: NetworkMonitor,
) : BaseViewModel(networkMonitor) {

    val profileDataFlow: StateFlow<ProfileScreenDTO> by lazy {
        combine(
            authenticatedUserRepository.loadAuthenticatedUser(),
            userDataRepository.userData
        ) { user, thema ->
            ProfileScreenDTO(
                user = user,
                brand = thema.themeBrand,
                useDynamicColor = thema.useDynamicColor,
                uIThemeConfigEnum = thema.themeConfigEnum
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState())
        }.onEach {
            notifyState(BaseUiState.ShowState)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileScreenDTO()
        )
    }

    fun logout() {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState())
                authenticatedUserRepository.logout()
                repository.deleteLists()
            },
            onSuccessEvent = {
                notifyState(BaseUiState.CloseState())
            }
        )
    }

    fun updateThemeBrand(themeBrand: ThemeBrandEnum) {
        viewModelScope.launch {
            userDataRepository.setThemeBrand(themeBrand)
        }
    }

    fun updateDarkThemeConfig(uIThemeConfigEnum: UIThemeConfigEnum) {
        viewModelScope.launch {
            userDataRepository.setDarkThemeConfig(uIThemeConfigEnum)
        }
    }

    fun updateDynamicColorPreference(useDynamicColor: Boolean) {
        viewModelScope.launch {
            userDataRepository.setDynamicColorPreference(useDynamicColor)
        }
    }
}