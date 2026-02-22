package com.vald3nir.shoppinglist.presentation.features.create.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.features.create.list.ui.CreateListEmptyState
import com.vald3nir.shoppinglist.presentation.features.create.list.ui.CreateListScreenContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun CreateListScreen(
    viewModel: CreateListViewModel = hiltViewModel(),
    onClickAddData: (shoppingListID: Long?) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val createListData by viewModel.createListDataFlow.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()

    if (uiState is BaseUiState.LoadingState) {
        ToolkitLoadingFullscreen()
        return
    }

    if (uiState is BaseUiState.EmptySate) {
        CreateListEmptyState(
            onClickAddData = { onClickAddData(createListData.listId) },
            onBackPressed = viewModel::navigateBack
        )
        return
    }

    CreateListScreenContent(
        createListData = createListData,
        searchQuery = searchQuery,
        onAddItem = { onClickAddData(createListData.listId) },
        onSaveList = { viewModel.saveShoppingList(listId = createListData.listId, listName = it) },
        onRemove = viewModel::removeShoppingListItem,
        filterItems = viewModel::onSearchQueryChanged,
        onBackPressed = viewModel::navigateBack
    )
}