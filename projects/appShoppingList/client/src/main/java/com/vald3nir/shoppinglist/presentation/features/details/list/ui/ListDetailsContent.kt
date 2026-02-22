package com.vald3nir.shoppinglist.presentation.features.details.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.components.AppTopBar
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.shoppinglist.domain.ListDetailDTO
import com.vald3nir.toolkit.core.utils.extensions.toMoney
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitAlertDialog
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitInputTextDialog
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.components.menus.ToolkitBottomSheet
import com.vald3nir.toolkit.designsystem.components.menus.ToolkitItemBottomSheet
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen

@Composable
internal fun ListDetailsContent(
    listDetails: ListDetailDTO = ListDetailDTO(),
    searchQuery: String = "",
    onChangeItemStatus: (id: Long?) -> Unit = {},
    filterItems: (key: String) -> Unit = {},
    onClickItemDetail: (itemId: Long?) -> Unit = {},
    onAddItem: () -> Unit = {},
    onEditListName: (listId: Long?, newName: String) -> Unit = { _, _ -> },
    onCloneList: (listId: Long?) -> Unit = {},
    onDeleteList: (listId: Long?) -> Unit = {},
    onShowItemsOnCart: () -> Unit = {},
    onShowItemsOffCart: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    val items = listDetails.getItemsFiltered()
    val (countAdded, countNotAdded, totalPrice) = listDetails.calculateShoppingCart()
    var showMenu by remember { mutableStateOf(false) }
    var showRemoveListAlert by remember { mutableStateOf(false) }
    var showEditListNameDialog by remember { mutableStateOf(false) }
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
                CartDetailsRow(label = stringResource(R.string.list_details_cart_on_size), value = countAdded.toString())
                CartDetailsRow(label = stringResource(R.string.list_details_cart_off_size), value = countNotAdded.toString())
                CartDetailsRow(label = stringResource(R.string.list_details_cart_total), value = totalPrice.toMoney())
                ToolkitDivider(modifier = Modifier.padding(start = ToolkitSpacingMd, top = ToolkitSpacingSm, end = ToolkitSpacingMd))
                ListDetailsFilter(showItemsOnCart = onShowItemsOnCart, showItemsOffCart = onShowItemsOffCart)
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
                        ItemListDetailsRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = ToolkitSpacingMd),
                            item = item,
                            showDivider = index != items.lastIndex,
                            onClickItemDetail = onClickItemDetail,
                            onChangeItemStatus = onChangeItemStatus,
                        )
                    })
                }
            }
        }
        ToolkitFixedButton(
            label = stringResource(R.string.list_details_add_item),
            onClick = onAddItem
        )
        if (showMenu) {
            ToolkitBottomSheet(
                items = listOf(
                    ToolkitItemBottomSheet.Default(
                        icon = ToolkitIconCatalog.Edit,
                        title = stringResource(R.string.list_details_alter_title),
                        onClick = {
                            showMenu = false
                            showEditListNameDialog = true
                        }
                    ),
                    ToolkitItemBottomSheet.Default(
                        icon = ToolkitIconCatalog.ContentCopy,
                        title = stringResource(R.string.list_details_clone_list),
                        onClick = {
                            showMenu = false
                            onCloneList(listDetails.listId)
                        }
                    ),
                    ToolkitItemBottomSheet.Default(
                        icon = ToolkitIconCatalog.Delete,
                        title = stringResource(R.string.list_details_delete_list),
                        onClick = {
                            showMenu = false
                            showRemoveListAlert = true
                        }
                    ),
                ),
                onDismissRequest = { showMenu = false }
            )
        }
        if (showRemoveListAlert) {
            ToolkitAlertDialog(
                title = stringResource(R.string.list_details_remove_list_confirm),
                description = stringResource(R.string.list_details_remove_list_description, listDetails.title),
                btnConfirmLabel = stringResource(R.string.remove),
                btnCancelLabel = stringResource(R.string.cancel),
                onConfirm = {
                    onDeleteList(listDetails.listId)
                    showRemoveListAlert = false
                },
                onCancel = { showRemoveListAlert = false }
            )
        }
        if (showEditListNameDialog) {
            ToolkitInputTextDialog(
                title = stringResource(R.string.list_details_edit_list_name_title),
                label = stringResource(R.string.list_details_edit_list_name_label),
                value = listDetails.title,
                btnConfirmLabel = stringResource(R.string.alter),
                btnCancelLabel = stringResource(R.string.cancel),
                onConfirm = { newName ->
                    onEditListName(listDetails.listId, newName)
                    showEditListNameDialog = false
                },
                onCancel = { showEditListNameDialog = false }
            )
        }
    }


}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground {
            ListDetailsContent()
        }
    }
}
