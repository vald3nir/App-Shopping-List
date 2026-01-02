package com.vald3nir.toolkit.designsystem.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun PreviewAlertDialog() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            ToolkitAlertDialog(
                title = "Deseja remover o item?",
                description = "o item xxxx será removido",
                btnConfirmLabel = "Remover",
                btnCancelLabel = "Cancelar",
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewInputTextDialog() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            ToolkitInputTextDialog(
                title = "Deseja remover o item?",
                label = "Exemplo de label",
                btnConfirmLabel = "Remover",
                btnCancelLabel = "Cancelar",
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewDatePickerDialog() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            ToolkitDatePickerDialog(
                context = LocalContext.current,
                onSelect = { date ->
                    println(date)
                }
            )
        }
    }
}