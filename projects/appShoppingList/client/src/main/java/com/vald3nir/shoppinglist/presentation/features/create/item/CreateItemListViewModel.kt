package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ProductsRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.services.sync.monitors.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CreateItemListViewModel @Inject constructor(
    private val itemShoppingListRepository: ItemShoppingListRepository,
    private val productsRepository: ProductsRepository,
    private val networkMonitor: NetworkMonitor,
) : BaseViewModel(networkMonitor) {

    val productNames: StateFlow<List<String>> = productsRepository.getProductNames().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun insertNewItemShoppingList(item: ItemShoppingListDTO) {
        safeLaunch(
            action = {
                itemShoppingListRepository.createNewShoppingListItem(item)
            },
            onSuccessEvent = {
                notifyState(BaseUiState.CloseState())
            }
        )
    }
}