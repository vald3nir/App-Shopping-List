package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon

@Composable
fun ToolkitAssistChip(
    imageVector: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    AssistChip(
        modifier = modifier,
        onClick = onClick,
        label = { Text(label) },
        enabled = enabled,
        leadingIcon = {
            ToolkitIcon(
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                imageVector = imageVector,
            )
        }
    )
}