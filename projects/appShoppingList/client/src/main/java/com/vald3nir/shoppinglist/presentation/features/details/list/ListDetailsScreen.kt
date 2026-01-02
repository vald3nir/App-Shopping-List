package com.vald3nir.shoppinglist.presentation.features.details.list

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.components.ScreenLoading
import com.vald3nir.shoppinglist.presentation.features.details.list.ui.ListDetailsContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState

@Composable
internal fun ListDetailsScreen(
    shoppingListID: Long?,
    viewModel: ListDetailsViewModel = hiltViewModel(),
    onClickAddData: (shoppingListID: Long?) -> Unit,
    onClickItemDetail: (itemId: Long?) -> Unit,
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listDetails by viewModel.listDetailsDataFlow.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val message by viewModel.uiMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(shoppingListID) {
        viewModel.loadShoppingList(shoppingListID)
    }

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            snackBarHostState.showSnackbar(message = message, duration = SnackbarDuration.Short)
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is BaseUiState.CloseState) {
            onBackPressed()
        }
    }

    if (uiState is BaseUiState.LoadingState) {
        ScreenLoading()
        return
    }

    ListDetailsContent(
        listDetails = listDetails,
        searchQuery = searchQuery,
        snackBarHostState = snackBarHostState,
        filterItems = viewModel::onSearchQueryChanged,
        onClickItemDetail = onClickItemDetail,
        onAddItem = { onClickAddData(shoppingListID) },
        onChangeItemStatus = { viewModel.changeItemStatus(itemId = it) },
        onEditListName = viewModel::editShoppingListName,
        onCloneList = viewModel::cloneShoppingList,
        onDeleteList = viewModel::deleteShoppingList,
        onShowItemsOnCart = { viewModel.showItemsOnCart() },
        onShowItemsOffCart = { viewModel.showItemsOffCart() },
        onBackPressed = onBackPressed
    )
}