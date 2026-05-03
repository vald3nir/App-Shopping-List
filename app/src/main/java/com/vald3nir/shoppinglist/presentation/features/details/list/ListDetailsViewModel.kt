package com.vald3nir.shoppinglist.presentation.features.details.list

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ListDetailDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.enums.ItemsFilterEnum
import com.vald3nir.shoppinglist.repository.AppRepository
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
    private val appRepository: AppRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    private val filterFlow = MutableStateFlow(ItemsFilterEnum.OFF_CART)
    fun filterShoppingCart(filterBy: ItemsFilterEnum) {
        filterFlow.value = filterBy
    }

    private val listIdFlow = MutableStateFlow<String?>(null)
    val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    private val shoppingListFlow: Flow<ShoppingListDTO?> = listIdFlow.flatMapLatest { id ->
        id?.let { appRepository.getShoppingListFlow(it) } ?: flowOf(null)
    }

    private val itemsShoppingListFlow: Flow<List<ItemShoppingListDTO>> = listIdFlow.flatMapLatest { id ->
        id?.let {
            appRepository.getItemsByListFlow(it)
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
            notifyState(BaseUiState.ShowState())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListDetailDTO()
        )
    }

    fun loadShoppingList(listId: String?) {
        listIdFlow.value = listId
    }

    fun editShoppingListName(listId: String?, newName: String) {
        safeLaunch(action = {
            appRepository.closeListEditing(listId = listId, title = newName)
        })
    }

    fun cloneShoppingList(listId: String?) {
        safeLaunch(
            action = {
                appRepository.cloneShoppingList(listId)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }

    fun deleteShoppingList(listId: String?) {
        safeLaunch(
            action = {
                appRepository.deleteShoppingList(listId)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }

    fun deleteItem(itemId: String?) {
        safeLaunch(
            action = { appRepository.removeItem(itemId) }
        )
    }

    fun changeItemStatus(itemId: String?) {
        safeLaunch(action = {
            appRepository.toggleIsAdd(itemId)
        })
    }
}