package com.vald3nir.shoppinglist.presentation.features.details.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.features.details.list.ui.ListDetailsContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun ListDetailsScreen(
    shoppingListID: Long?,
    viewModel: ListDetailsViewModel = hiltViewModel(),
    onClickAddData: (shoppingListID: Long?) -> Unit,
    onClickItemDetail: (itemId: Long?) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listDetails by viewModel.listDetailsDataFlow.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()

    LaunchedEffect(shoppingListID) {
        viewModel.loadShoppingList(shoppingListID)
    }

    if (uiState is BaseUiState.LoadingState) {
        ToolkitLoadingFullscreen()
        return
    }

    ListDetailsContent(
        listDetails = listDetails,
        searchQuery = searchQuery,
        filterItems = viewModel::onSearchQueryChanged,
        onClickItemDetail = onClickItemDetail,
        onAddItem = { onClickAddData(shoppingListID) },
        onChangeItemStatus = { viewModel.changeItemStatus(itemId = it) },
        onEditListName = viewModel::editShoppingListName,
        onCloneList = viewModel::cloneShoppingList,
        onDeleteList = viewModel::deleteShoppingList,
        onShowItemsOnCart = { viewModel.showItemsOnCart() },
        onShowItemsOffCart = { viewModel.showItemsOffCart() },
        onBackPressed = viewModel::navigateBack
    )
}