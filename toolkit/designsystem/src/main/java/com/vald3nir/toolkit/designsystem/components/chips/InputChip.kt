package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.foundation.layout.size
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog

@Composable
fun ToolkitInputChip(
    imageVector: ImageVector? = null,
    label: String,
    selected: Boolean = true,
    onDismiss: () -> Unit,
) {
    var enabled by remember { mutableStateOf(selected) }
    if (!enabled) return

    InputChip(
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        label = { Text(label) },
        selected = enabled,
        avatar = {
            if (imageVector != null) {
                ToolkitIcon(
                    imageVector = imageVector,
                    modifier = Modifier.size(InputChipDefaults.AvatarSize)
                )
            }
        },
        trailingIcon = {
            ToolkitIcon(
                imageVector = ToolkitIconCatalog.Close,
                modifier = Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
    )
}