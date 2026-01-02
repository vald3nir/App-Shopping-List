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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.vald3nir.toolkit.designsystem.components.defaultSpace
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitInputText(
    inputValue: String = "",
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    singleLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = inputValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultSpace),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        leadingIcon = leftIcon?.let { icon ->
            { icon.BuildIconButton(onClick = onClickLeftIcon, tint = iconTint) }
        },
        trailingIcon = {
            if (inputValue.isNotEmpty()) {
                ToolkitIconCatalog.Close.BuildIconButton(
                    tint = iconTint,
                    onClick = { onValueChange("") }
                )
            } else {
                ToolkitIconCatalog.Edit.BuildIconButton(
                    tint = iconTint,
                    onClick = {}
                )
            }
        },
        supportingText = {
            errorValue?.let {
                ToolkitText(
                    text = it,
                    style = ToolkitTextStyle.LabelLarge,
                    textColor = Color.Red
                )
            }
        }
    )
}