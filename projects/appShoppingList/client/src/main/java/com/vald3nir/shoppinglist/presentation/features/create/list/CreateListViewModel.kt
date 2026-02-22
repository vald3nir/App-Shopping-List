package com.vald3nir.shoppinglist.presentation.features.create.list

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.domain.CreateListDTO
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
    private val shoppingListRepository: ShoppingListRepository,
    private val itemShoppingListRepository: ItemShoppingListRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    val createListDataFlow: StateFlow<CreateListDTO> by lazy {
        combine(
            shoppingListRepository.openListEditing(),
            searchQuery,
        ) { list, query ->
            CreateListDTO(
                listId = list.id,
                title = list.title.orEmpty(),
                items = list.items,
                query = query.trim().lowercase()
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            if (it.items.isEmpty()) {
                notifyState(BaseUiState.EmptySate)
            } else {
                notifyState(BaseUiState.ShowState)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CreateListDTO()
        )
    }

    fun removeShoppingListItem(itemId: Long?) {
        safeLaunch(
            action = {
                itemShoppingListRepository.removeItem(itemId)
            },
        )
    }

    fun saveShoppingList(listId: Long?, listName: String) {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState(true))
                shoppingListRepository.closeListEditing(listId = listId, title = listName)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }
}