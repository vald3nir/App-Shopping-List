package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ProductsRepository
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CreateItemListViewModel @Inject constructor(
    private val itemShoppingListRepository: ItemShoppingListRepository,
    productsRepository: ProductsRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    val productNames: StateFlow<List<String>> = productsRepository.getProductNames().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun insertNewItemList(item: ItemShoppingListDTO) {
        safeLaunch(
            action = {
                itemShoppingListRepository.insertNewItemList(item)
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }
}