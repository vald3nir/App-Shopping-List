package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.menus.ToolkitBottomSheet
import com.vald3nir.toolkit.designsystem.components.menus.ToolkitItemBottomSheet

@Composable
internal fun ListDetailsBottomSheet(
    onClickEditListName: () -> Unit,
    onClickCloneList: () -> Unit,
    onClickDeleteList: () -> Unit,
    onCancel: () -> Unit,
) {
    ToolkitBottomSheet(
        items = listOf(
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIconCatalog.Edit,
                title = stringResource(R.string.list_details_alter_title),
                onClick = onClickEditListName
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIconCatalog.ContentCopy,
                title = stringResource(R.string.list_details_clone_list),
                onClick = onClickCloneList
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIconCatalog.Delete,
                title = stringResource(R.string.list_details_delete_list),
                onClick = onClickDeleteList
            ),
        ),
        onDismissRequest = onCancel
    )
}