package com.vald3nir.shoppinglist.presentation.features.create.list

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
import com.vald3nir.shoppinglist.presentation.features.create.list.ui.CreateListEmptyState
import com.vald3nir.shoppinglist.presentation.features.create.list.ui.CreateListScreenContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState

@Composable
internal fun CreateListScreen(
    viewModel: CreateListViewModel = hiltViewModel(),
    onClickAddData: (shoppingListID: Long?) -> Unit,
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message by viewModel.uiMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val createListData by viewModel.createListDataFlow.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()

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

    if (uiState is BaseUiState.EmptySate) {
        CreateListEmptyState(
            onClickAddData = { onClickAddData(createListData.listId) },
            onBackPressed = onBackPressed
        )
        return
    }

    CreateListScreenContent(
        createListData = createListData,
        searchQuery = searchQuery,
        snackBarHostState = snackBarHostState,
        onAddItem = { onClickAddData(createListData.listId) },
        onSaveList = { viewModel.saveShoppingList(listId = createListData.listId, listName = it) },
        onRemove = viewModel::removeShoppingListItem,
        filterItems = viewModel::onSearchQueryChanged,
        onBackPressed = onBackPressed
    )
}