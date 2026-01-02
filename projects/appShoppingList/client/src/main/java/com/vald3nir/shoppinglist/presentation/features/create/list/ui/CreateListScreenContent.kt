package com.vald3nir.shoppinglist.presentation.features.create.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.CreateListDTO
import com.vald3nir.shoppinglist.domain.providers.CreateListProvider
import com.vald3nir.shoppinglist.presentation.components.buildTopBar
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFloatingButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitInputTextDialog
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.templates.ToolkitBaseContent

@Composable
internal fun CreateListScreenContent(
    createListData: CreateListDTO = CreateListDTO(),
    searchQuery: String = "",
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onRemove: (id: Long?) -> Unit = {},
    onSaveList: (listName: String) -> Unit = {},
    filterItems: (key: String) -> Unit = {},
    onAddItem: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var showSaveDialog: Boolean by remember { mutableStateOf(false) }
    ToolkitBaseContent(
        snackBarHostState = snackBarHostState,
        topBar = buildTopBar(
            title = stringResource(R.string.new_list_title),
            extraIcon = ToolkitIconCatalog.Save,
            onBackPressed = onBackPressed,
            onClickExtraIcon = { showSaveDialog = true }
        ),
        floatingActionButton = {
            ToolkitFloatingButton(
                imageVector = ToolkitIconCatalog.Add,
                onClick = onAddItem
            )
        },
        content = {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column {
                    ToolkitSearchFiled(
                        label = stringResource(R.string.create_list_search_item),
                        searchQuery = searchQuery,
                        onValueChange = filterItems,
                    )
                    val items = createListData.getItemsFiltered()
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .navigationBarsPadding()
                    ) {
                        itemsIndexed(items = items, itemContent = { index, item ->
                            CreateShoppingListRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = ToolkitSpacingMd),
                                item = item,
                                showDivider = index != items.lastIndex,
                                onRemove = onRemove,
                            )
                        })
                    }
                }
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
    )
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