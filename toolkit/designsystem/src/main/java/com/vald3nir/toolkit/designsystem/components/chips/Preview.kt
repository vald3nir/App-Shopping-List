package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier.size(150.dp, 300.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                ToolkitAssistChip(
                    enabled = true,
                    onClick = { },
                    label = "Assist chip",
                    imageVector = ToolkitIconCatalog.Settings
                )

                ToolkitFilterChip(label = "Chip", selected = true, onSelectedChange = {})

                ToolkitInputChip(
                    imageVector = ToolkitIconCatalog.Bookmark,
                    label = "input chip",
                    selected = true,
                    onDismiss = {}
                )
            }
        }
    }
}