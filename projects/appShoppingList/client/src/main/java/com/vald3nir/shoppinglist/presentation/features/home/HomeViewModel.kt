package com.vald3nir.shoppinglist.presentation.features.home

import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.vald3nir.shoppinglist.domain.HomeScreenDTO
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.core.sync.workers.SyncListsWorker
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.services.sync.monitors.NetworkMonitor
import com.vald3nir.toolkit.core.services.sync.worker.createSyncWorkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val workManager: WorkManager,
    authenticatedUserRepository: AuthenticatedUserRepository,
    repository: ShoppingListRepository,
    networkMonitor: NetworkMonitor,
) : BaseViewModel(networkMonitor) {

    fun syncLists() {
        workManager.enqueueUniqueWork("SyncLists", ExistingWorkPolicy.KEEP, createSyncWorkRequest<SyncListsWorker>())
    }

    val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    val homeDataFlow: StateFlow<HomeScreenDTO?> by lazy {
        combine(
            authenticatedUserRepository.loadAuthenticatedUser(),
            repository.getShoppingLists(),
            searchQuery
        ) { user, shoppingList, query ->
            val normalizedQuery = query.trim().lowercase()
            if (normalizedQuery.isEmpty()) {
                HomeScreenDTO(user = user, lists = shoppingList)
            } else {
                HomeScreenDTO(user = user, lists = shoppingList.filter { it.filter(normalizedQuery) })
            }
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            if (it.lists.isNullOrEmpty()) {
                notifyState(BaseUiState.EmptySate)
            } else {
                notifyState(BaseUiState.ShowState)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
    }
}