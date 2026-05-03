package com.vald3nir.shoppinglist.presentation.features.details.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ListDetailDTO
import com.vald3nir.shoppinglist.domain.enums.ItemsFilterEnum
import com.vald3nir.shoppinglist.presentation.components.AppPreview
import com.vald3nir.shoppinglist.presentation.components.AppTopBar
import com.vald3nir.shoppinglist.presentation.components.ListDetailsBottomSheet
import com.vald3nir.shoppinglist.presentation.components.SelectItemListRow
import com.vald3nir.shoppinglist.presentation.components.ShoppingCartDetails
import com.vald3nir.shoppinglist.presentation.components.ShoppingCartFilter
import com.vald3nir.shoppinglist.presentation.components.ShowDeleteListDialog
import com.vald3nir.shoppinglist.presentation.components.ShowEditListNameDialog
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun ListDetailsScreen(
    shoppingListID: String?,
    viewModel: ListDetailsViewModel = hiltViewModel(),
    onClickAddData: (shoppingListID: String?) -> Unit,
    onClickItemDetail: (itemId: String?) -> Unit,
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

    Content(
        listDetails = listDetails,
        searchQuery = searchQuery,
        filterItems = viewModel::onSearchQueryChanged,
        onClickItemDetail = onClickItemDetail,
        onAddItem = { onClickAddData(shoppingListID) },
        onChangeItemStatus = { viewModel.changeItemStatus(itemId = it) },
        onEditListName = viewModel::editShoppingListName,
        onCloneList = viewModel::cloneShoppingList,
        onDeleteList = viewModel::deleteShoppingList,
        onDeleteItem = viewModel::deleteItem,
        onFilterShoppingCart = viewModel::filterShoppingCart,
        onBackPressed = viewModel::navigateBack
    )
}

@Composable
private fun Content(
    listDetails: ListDetailDTO = ListDetailDTO(),
    searchQuery: String = "",
    onChangeItemStatus: (id: String?) -> Unit = {},
    filterItems: (key: String) -> Unit = {},
    onClickItemDetail: (itemId: String?) -> Unit = {},
    onAddItem: () -> Unit = {},
    onEditListName: (listId: String?, newName: String) -> Unit = { _, _ -> },
    onCloneList: (listId: String?) -> Unit = {},
    onDeleteList: (listId: String?) -> Unit = {},
    onDeleteItem: (itemId: String?) -> Unit = {},
    onFilterShoppingCart: (ItemsFilterEnum) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {

    val items = listDetails.getItemsFiltered()
    val (countAdded, countNotAdded, totalPrice) = listDetails.calculateShoppingCart()
    var showMenu by remember { mutableStateOf(false) }
    var showRemoveListAlert by remember { mutableStateOf(false) }
    var showEditListNameDialog by remember { mutableStateOf(false) }
    var showBtnAddItems by remember { mutableStateOf(true) }

    ToolkitColumn {
        AppTopBar(
            title = listDetails.title,
            onBackPressed = onBackPressed,
            extraIcon = ToolkitIconCatalog.Settings,
            onClickExtraIcon = { showMenu = true }
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .imePadding()
        ) {
            ToolkitSearchFiled(
                label = stringResource(R.string.create_list_search_item),
                searchQuery = searchQuery,
                onValueChange = filterItems,
            )
            if (searchQuery.isEmpty()) {
                ShoppingCartDetails(countAdded = countAdded, countNotAdded = countNotAdded, totalPrice = totalPrice)
                ShoppingCartFilter(
                    showItemsOnCart = {
                        showBtnAddItems = false
                        onFilterShoppingCart(ItemsFilterEnum.ON_CART)
                    },
                    showItemsOffCart = {
                        showBtnAddItems = true
                        onFilterShoppingCart(ItemsFilterEnum.OFF_CART)
                    }
                )
            }
            if (items.isEmpty()) {
                ToolkitEmptyStateScreen(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.list_details_empty_state_message),
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .navigationBarsPadding()
                ) {
                    itemsIndexed(items = items, itemContent = { index, item ->
                        SelectItemListRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = ToolkitSpacingMd),
                            item = item,
                            showDivider = index != items.lastIndex,
                            onClickItemDetail = onClickItemDetail,
                            onChangeItemStatus = onChangeItemStatus,
                            onDeleteItem = onDeleteItem,
                        )
                    })
                }
            }
        }
        if (showBtnAddItems) {
            ToolkitFixedButton(
                label = stringResource(R.string.list_details_add_item),
                onClick = onAddItem
            )
        }
        if (showMenu) {
            ListDetailsBottomSheet(
                onClickEditListName = {
                    showMenu = false
                    showEditListNameDialog = true
                },
                onClickCloneList = {
                    showMenu = false
                    onCloneList(listDetails.listId)
                },
                onClickDeleteList = {
                    showMenu = false
                    showRemoveListAlert = true
                },
                onCancel = { showMenu = false }
            )
        }
        if (showRemoveListAlert) {
            ShowDeleteListDialog(
                listName = listDetails.title,
                onConfirm = {
                    onDeleteList(listDetails.listId)
                    showRemoveListAlert = false
                },
                onCancel = { showRemoveListAlert = false }
            )
        }
        if (showEditListNameDialog) {
            ShowEditListNameDialog(
                listName = listDetails.title,
                onConfirm = { newName ->
                    onEditListName(listDetails.listId, newName)
                    showEditListNameDialog = false
                },
                onCancel = { showEditListNameDialog = false }
            )
        }
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        Content()
    }
}