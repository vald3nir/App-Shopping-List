package com.vald3nir.shoppinglist.presentation.features.create.list.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.components.AppTopBar
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.shoppinglist.domain.CreateListDTO
import com.vald3nir.shoppinglist.domain.providers.CreateListProvider
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitInputTextDialog
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun CreateListScreenContent(
    createListData: CreateListDTO = CreateListDTO(),
    searchQuery: String = "",
    onRemove: (id: Long?) -> Unit = {},
    onSaveList: (listName: String) -> Unit = {},
    filterItems: (key: String) -> Unit = {},
    onAddItem: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var showSaveDialog by remember { mutableStateOf(false) }
    val items = createListData.getItemsFiltered()
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.new_list_title),
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
                CreateShoppingListRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ToolkitSpacingMd),
                    item = item,
                    showDivider = index != items.lastIndex,
                    onRemove = onRemove,
                )
            }
        }
        ToolkitBaseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ToolkitSpacingMd),
            text = stringResource(R.string.add_item),
            leadingIcon = ToolkitIconCatalog.Add,
            onClick = onAddItem
        )
        if (showSaveDialog) {
            ToolkitInputTextDialog(
                title = stringResource(R.string.create_list_input_list_name),
                label = stringResource(R.string.create_list_name),
                value = stringResource(R.string.create_list_default_title),
                btnConfirmLabel = stringResource(R.string.create_list_btn_save),
                btnCancelLabel = stringResource(R.string.create_list_btn_cancel),
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

@ThemePreviews
@Composable
private fun PreviewContent(@PreviewParameter(CreateListProvider::class) createListData: CreateListDTO) {
    AppTheme {
        ToolkitBackground {
            CreateListScreenContent(createListData = createListData)
        }
    }
}