package com.vald3nir.toolkit.designsystem.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.vald3nir.toolkit.designsystem.components.defaultSpace
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog

@Composable
fun ToolkitSearchFiled(
    searchQuery: String = "",
    placeholder: String = "Pesquisar",
    label: String = "",
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultSpace),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                ToolkitIconCatalog.Close.BuildIconButton(
                    tint = MaterialTheme.colorScheme.onSurface,
                    onClick = { onValueChange("") }
                )
            } else {
                ToolkitIconCatalog.Search.BuildIconButton(
                    tint = MaterialTheme.colorScheme.onSurface,
                    onClick = {}
                )
            }
        }
    )
}