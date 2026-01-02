package com.vald3nir.toolkit.designsystem.components.inputs

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ToolkitInputInteger(
    inputValue: Int = 0,
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (Int) -> Unit = {},
) {
    ToolkitInputText(
        inputValue = if (inputValue == 0) "" else inputValue.toString(),
        label = label,
        placeholder = placeholder,
        errorValue = errorValue,
        iconTint = iconTint,
        singleLine = true,
        leftIcon = leftIcon,
        onClickLeftIcon = onClickLeftIcon,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            // Proteção simples contra números gigantes ou vazios
            onValueChange(it.take(9).toIntOrNull() ?: 0)
        },
    )
}