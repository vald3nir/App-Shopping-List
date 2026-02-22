package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog

@Composable
fun ToolkitTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        content = {
            ToolkitButtonContent(
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon
            )
        },
    )
}

@Composable
fun ToolkitLinkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
) {
    ToolkitTextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        label = label,
        trailingIcon = ToolkitIconCatalog.ChevronRight
    )
}