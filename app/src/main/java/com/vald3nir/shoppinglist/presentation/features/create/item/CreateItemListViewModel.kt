package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.dto.CreateItemListContentDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
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
internal class CreateItemListViewModel @Inject constructor(
    private val appRepository: AppRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    private val shoppingListIDFlow = MutableStateFlow<String?>(null)

    private val topItemsFlow: Flow<List<ItemShoppingListDTO>> = shoppingListIDFlow.flatMapLatest { id ->
        id?.let { appRepository.loadTopFrequentsItemLists(it) } ?: flowOf(emptyList())
    }

    fun loadScreenContent(shoppingListID: String?) {
        shoppingListIDFlow.value = shoppingListID
    }

    val contentDataFlow: StateFlow<CreateItemListContentDTO> by lazy {
        combine(
            topItemsFlow,
            appRepository.getProductNames(),
        ) { topItems, productNames ->
            CreateItemListContentDTO(
                topItems = topItems,
                productNames = productNames
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            notifyState(BaseUiState.ShowState())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CreateItemListContentDTO()
        )
    }

    fun insertNewItemList(item: ItemShoppingListDTO) {
        safeLaunch(
            action = {
                appRepository.insertNewItemList(
                    item.copy(
                        shoppingListId = shoppingListIDFlow.value
                    )
                )
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }
}