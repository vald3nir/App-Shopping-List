package com.vald3nir.shoppinglist.presentation.features.details.list

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.enums.ItemsFilterEnum
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.domain.ListDetailDTO
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ListDetailsViewModel @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val itemShoppingListRepository: ItemShoppingListRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    private val filterFlow = MutableStateFlow(ItemsFilterEnum.OFF_CART)
    fun showItemsOnCart() {
        filterFlow.value = ItemsFilterEnum.ON_CART
    }

    fun showItemsOffCart() {
        filterFlow.value = ItemsFilterEnum.OFF_CART
    }

    private val listIdFlow = MutableStateFlow<Long?>(null)
    val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    private val shoppingListFlow: Flow<ShoppingListDTO?> = listIdFlow.flatMapLatest { id ->
        id?.let { shoppingListRepository.getShoppingListFlow(it) } ?: flowOf(null)
    }

    private val itemsShoppingListFlow: Flow<List<ItemShoppingListDTO>> = listIdFlow.flatMapLatest { id ->
        id?.let {
            itemShoppingListRepository.getItemsByListFlow(it)
        } ?: flowOf(emptyList())
    }

    val listDetailsDataFlow: StateFlow<ListDetailDTO> by lazy {
        combine(
            shoppingListFlow,
            itemsShoppingListFlow,
            filterFlow,
            searchQuery,
        ) { list, items, filter, query ->
            ListDetailDTO(
                listId = list?.id,
                title = list?.title.orEmpty(),
                items = items,
                filter = filter,
                query = query.trim().lowercase()
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            notifyState(BaseUiState.ShowState)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListDetailDTO()
        )
    }

    fun loadShoppingList(listId: Long?) {
        listIdFlow.value = listId
    }

    fun editShoppingListName(listId: Long?, newName: String) {
        safeLaunch(action = {
            shoppingListRepository.closeListEditing(listId = listId, title = newName)
        })
    }

    fun cloneShoppingList(listId: Long?) {
        safeLaunch(
            action = {
                shoppingListRepository.cloneShoppingList(listId)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }

    fun deleteShoppingList(listId: Long?) {
        safeLaunch(
            action = {
                shoppingListRepository.deleteShoppingList(listId)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }

    fun changeItemStatus(itemId: Long?) {
        safeLaunch(action = {
            itemShoppingListRepository.toggleIsAdd(itemId)
        })
    }
}