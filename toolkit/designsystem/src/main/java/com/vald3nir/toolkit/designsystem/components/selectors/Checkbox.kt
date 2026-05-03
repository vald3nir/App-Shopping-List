package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Checkbox(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(100.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ToolkitCheckBox(checked = false)
            ToolkitSpaceHeight()
            ToolkitCheckBox(checked = true)
        }
    }
}