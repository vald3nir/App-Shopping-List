package com.vald3nir.shoppinglist.presentation.features.create.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.CreateListDTO
import com.vald3nir.shoppinglist.domain.providers.CreateListProvider
import com.vald3nir.shoppinglist.presentation.components.AppPreview
import com.vald3nir.shoppinglist.presentation.components.AppTopBar
import com.vald3nir.shoppinglist.presentation.components.CreateItemListRow
import com.vald3nir.shoppinglist.presentation.components.ShowCreateListDialog
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun CreateListScreen(
    viewModel: CreateListViewModel = hiltViewModel(),
    onClickAddData: (shoppingListID: String?) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val createListData by viewModel.createListDataFlow.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()

    if (uiState is BaseUiState.LoadingState) {
        ToolkitLoadingFullscreen()
        return
    }

    if (uiState is BaseUiState.EmptySate) {
        EmptyState(
            onClickAddData = { onClickAddData(createListData.listId) },
            onBackPressed = viewModel::navigateBack
        )
        return
    }

    ScreenContent(
        createListData = createListData,
        searchQuery = searchQuery,
        onAddItem = { onClickAddData(createListData.listId) },
        onSaveList = { viewModel.saveShoppingList(listId = createListData.listId, listName = it) },
        onRemove = viewModel::removeShoppingListItem,
        filterItems = viewModel::onSearchQueryChanged,
        onBackPressed = viewModel::navigateBack
    )
}

@Composable
private fun ScreenContent(
    createListData: CreateListDTO = CreateListDTO(),
    searchQuery: String = "",
    onRemove: (id: String?) -> Unit = {},
    onSaveList: (listName: String) -> Unit = {},
    filterItems: (key: String) -> Unit = {},
    onAddItem: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var showSaveDialog by remember { mutableStateOf(false) }
    val items = createListData.getItemsFiltered()
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.create_new_list_title),
            onBackPressed = onBackPressed,
            extraIcon = ToolkitIconCatalog.Save,
            onClickExtraIcon = { showSaveDialog = true }
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .imePadding(),
            contentPadding = PaddingValues(bottom = ToolkitSpacingMd)
        ) {
            item {
                ToolkitSearchFiled(
                    label = stringResource(R.string.create_list_search_item),
                    searchQuery = searchQuery,
                    onValueChange = filterItems,
                )
            }
            itemsIndexed(
                items = items,
                key = { _, item -> item.id ?: 0 }
            ) { index, item ->
                CreateItemListRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ToolkitSpacingMd),
                    item = item,
                    showDivider = index != items.lastIndex,
                    onClickRemove = onRemove,
                )
            }
        }
        ToolkitBaseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ToolkitSpacingMd),
            text = stringResource(R.string.insert_item_btn_add),
            leadingIcon = ToolkitIconCatalog.Add,
            onClick = onAddItem
        )
        if (showSaveDialog) {
            ShowCreateListDialog(
                onConfirm = {
                    showSaveDialog = false
                    onSaveList(it)
                },
                onCancel = {
                    showSaveDialog = false
                }
            )
        }
    }
}

@Composable
private fun EmptyState(
    onClickAddData: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.create_new_list_title),
            onBackPressed = onBackPressed,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            ToolkitEmptyStateScreen(
                title = stringResource(R.string.create_list_empty_state_message),
                btnText = stringResource(R.string.create_list_btn_add_items),
                imageVector = ToolkitIconCatalog.ShoppingCart,
                onClickBtn = onClickAddData,
            )
        }
    }
}


@AppPreview
@Composable
private fun PreviewContent(@PreviewParameter(CreateListProvider::class) createListData: CreateListDTO) {
    ToolkitPreviewContainer {
        ScreenContent(createListData = createListData)
    }
}

@AppPreview
@Composable
private fun PreviewEmptyState() {
    ToolkitPreviewContainer {
        EmptyState()
    }
}