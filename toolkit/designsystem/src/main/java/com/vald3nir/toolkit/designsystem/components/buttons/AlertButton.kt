package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AlertButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    leadingIcon: ImageVector? = null,
) {
    ToolkitBaseButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        text = text,
        leadingIcon = leadingIcon,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
    )
}