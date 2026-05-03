package com.vald3nir.shoppinglist.presentation.features.create.list

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.dto.CreateListDTO
import com.vald3nir.shoppinglist.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
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
internal class CreateListViewModel @Inject constructor(
    private val appRepository: AppRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    val createListDataFlow: StateFlow<CreateListDTO> by lazy {
        combine(
            appRepository.openListEditing(),
            searchQuery,
        ) { list, query ->
            CreateListDTO(
                listId = list.id,
                title = list.title.orEmpty(),
                items = list.items.sortedBy { it.product },
                query = query.trim().lowercase()
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            if (it.items.isEmpty()) {
                notifyState(BaseUiState.EmptySate)
            } else {
                notifyState(BaseUiState.ShowState())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CreateListDTO()
        )
    }

    fun removeShoppingListItem(itemId: String?) {
        safeLaunch(
            action = {
                appRepository.removeItem(itemId)
            },
        )
    }

    fun saveShoppingList(listId: String?, listName: String) {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState(true))
                appRepository.closeListEditing(listId = listId, title = listName)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }
}