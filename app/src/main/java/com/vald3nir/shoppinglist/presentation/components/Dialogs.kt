package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitAlertDialog
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitInputTextDialog

@Composable
internal fun ShowCreateListDialog(
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitInputTextDialog(
        title = stringResource(R.string.create_list_input_list_name),
        label = stringResource(R.string.create_list_name),
        value = stringResource(R.string.create_list_default_title),
        btnConfirmLabel = stringResource(R.string.create_list_btn_save),
        btnCancelLabel = stringResource(R.string.create_list_btn_cancel),
        onConfirm = onConfirm,
        onCancel = onCancel,
    )
}

@Composable
internal fun ShowDeleteListDialog(
    listName: String,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitAlertDialog(
        title = stringResource(R.string.list_details_remove_list_confirm),
        description = stringResource(R.string.list_details_remove_list_description, listName),
        btnConfirmLabel = stringResource(R.string.remove),
        btnCancelLabel = stringResource(R.string.cancel),
        onConfirm = onConfirm,
        onCancel = onCancel,
    )
}

@Composable
internal fun ShowDeleteItemListDialog(
    itemName: String,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitAlertDialog(
        title = stringResource(R.string.edit_item_remove_confirm_title),
        description = stringResource(R.string.edit_item_remove_confirm_description, itemName),
        btnConfirmLabel = stringResource(R.string.remove),
        btnCancelLabel = stringResource(R.string.cancel),
        onConfirm = onConfirm,
        onCancel = onCancel,
    )
}

@Composable
internal fun ShowEditListNameDialog(
    listName: String,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitInputTextDialog(
        title = stringResource(R.string.list_details_edit_list_name_title),
        label = stringResource(R.string.list_details_edit_list_name_label),
        value = listName,
        btnConfirmLabel = stringResource(R.string.alter),
        btnCancelLabel = stringResource(R.string.cancel),
        onConfirm = onConfirm,
        onCancel = onCancel,
    )
}

@Composable
internal fun ShowLogoutDialog(
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitAlertDialog(
        title = stringResource(R.string.profile_dialog_logout_title),
        description = stringResource(R.string.profile_dialog_logout_description),
        btnConfirmLabel = stringResource(R.string.logout),
        btnCancelLabel = stringResource(R.string.cancel),
        onConfirm = onConfirm,
        onCancel = onCancel,
    )
}