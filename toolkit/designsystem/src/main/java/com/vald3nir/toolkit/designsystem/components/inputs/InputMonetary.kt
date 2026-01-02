package com.vald3nir.toolkit.designsystem.components.inputs

import android.icu.text.NumberFormat
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import java.util.Locale

@Composable
fun ToolkitInputMonetary(
    inputValue: Double = 0.0,
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (Double) -> Unit = {},
) {
    val formatter = remember {
        NumberFormat.getCurrencyInstance(Locale("pt", "BR")).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }
    }
    var text by remember(inputValue) {
        mutableStateOf(formatter.format(inputValue))
    }
    ToolkitInputText(
        inputValue = text,
        label = label,
        placeholder = placeholder,
        errorValue = errorValue,
        iconTint = iconTint,
        singleLine = true,
        leftIcon = leftIcon,
        onClickLeftIcon = onClickLeftIcon,
        keyboardType = KeyboardType.Number,
        onValueChange = { newValue ->
            val digitsOnly = newValue.filter { it.isDigit() }
            if (digitsOnly.isEmpty()) {
                text = formatter.format(0.0)
                onValueChange(0.0)
            } else {
                val value = digitsOnly.toDouble() / 100
                text = formatter.format(value)
                onValueChange(value)
            }
        },
    )
}