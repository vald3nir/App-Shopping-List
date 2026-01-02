package com.vald3nir.toolkit.designsystem.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitAlertDialog(
    title: String,
    description: String? = null,
    btnConfirmLabel: String,
    btnCancelLabel: String,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            ToolkitText(text = title, style = ToolkitTextStyle.TitleSmall)
        },
        text = {
            if (!description.isNullOrBlank()) {
                ToolkitText(text = description, style = ToolkitTextStyle.LabelMedium)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                ToolkitText(text = btnCancelLabel, style = ToolkitTextStyle.LabelMedium)
            }
        },
        confirmButton = {
            ToolkitBaseButton(onClick = { onConfirm() }, text = btnConfirmLabel)
        },
    )
}