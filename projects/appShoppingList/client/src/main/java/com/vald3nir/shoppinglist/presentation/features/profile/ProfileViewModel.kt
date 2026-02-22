package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.domain.ProfileScreenDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import com.vald3nir.toolkit.themas.repository.ThemaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val authenticatedUserRepository: AuthenticatedUserRepository,
    private val themaRepository: ThemaRepository,
    private val repository: ShoppingListRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    val profileDataFlow: StateFlow<ProfileScreenDTO> by lazy {
        combine(
            authenticatedUserRepository.loadAuthenticatedUser(),
            themaRepository.appThemaFlow
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
                navigateBack()
            }
        )
    }
}