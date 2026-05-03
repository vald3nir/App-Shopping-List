package com.vald3nir.shoppinglist.presentation.features.details.edititem

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.dto.ScreenItemListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class EditItemListViewModel @Inject constructor(
    private val appRepository: AppRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    var itemListFlow = MutableStateFlow(ItemShoppingListDTO())

    fun loadItemShoppingList(itemId: String?) {
        safeLaunch(
            action = {
                itemListFlow.value = appRepository.getItem(itemId) ?: ItemShoppingListDTO()
            }
        )
    }

    val screenDataFlow: StateFlow<ScreenItemListDTO> by lazy {
        combine(
            itemListFlow,
            appRepository.getProductNames(),
        ) { item, productNames ->
            ScreenItemListDTO(item = item, productNames = productNames)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenItemListDTO()
        )
    }

    fun updateItem(item: ItemShoppingListDTO) {
        itemListFlow.value = item
    }

    fun onSaveItem() {
        safeLaunch(
            action = {
                appRepository.updateItem(itemListFlow.value)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }

    fun searchProductNameByBarCode(barCode: String?) {
        safeLaunch(
            action = {
                val product = appRepository.searchProductName(barCode)
                itemListFlow.value = itemListFlow.value.copy(product = product)
            }
        )
    }
}