package com.vald3nir.shoppinglist.presentation.features.details.item

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.ItemListDetailDTO
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.repository.CategoryRepository
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ProductsRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.services.sync.monitors.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ItemDetailViewModel @Inject constructor(
    private val itemShoppingListRepository: ItemShoppingListRepository,
    private val productsRepository: ProductsRepository,
    private val categoryRepository: CategoryRepository,
    networkMonitor: NetworkMonitor,
) : BaseViewModel(networkMonitor) {

    private val itemIdFlow = MutableStateFlow<Long?>(null)
    fun loadItemShoppingList(itemId: Long?) {
        itemIdFlow.value = itemId
    }

    private val itemShoppingListFlow: Flow<ItemShoppingListDTO?> = itemIdFlow.flatMapLatest { id ->
        id?.let { itemShoppingListRepository.getItemFlow(it) } ?: flowOf(null)
    }

    val itemDetailsDataFlow: StateFlow<ItemListDetailDTO> by lazy {
        combine(
            itemShoppingListFlow,
            productsRepository.getProductNames(),
            categoryRepository.getCategoriesFlow()
        ) { item, productsName, categories ->
            ItemListDetailDTO(
                item = item ?: ItemShoppingListDTO(),
                productNames = productsName,
                categories = categories
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ItemListDetailDTO()
        )
    }

    fun updateItem(item: ItemShoppingListDTO) {
        safeLaunch(
            action = {
                itemShoppingListRepository.updateItem(item)
            },
            onSuccessEvent = {
                notifyState(BaseUiState.CloseState())
            }
        )
    }

    fun deleteItem(itemId: Long?) {
        safeLaunch(
            action = {
                itemShoppingListRepository.removeItem(itemId)
            },
            onSuccessEvent = {
                notifyState(BaseUiState.CloseState())
            }
        )
    }
}