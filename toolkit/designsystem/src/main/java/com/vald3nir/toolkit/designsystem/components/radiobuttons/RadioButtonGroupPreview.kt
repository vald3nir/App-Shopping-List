package com.vald3nir.toolkit.designsystem.components.radiobuttons

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun PreviewGrid() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitRadioButtonGroup(
                    items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"),
                    viewType = ToolkitRadioButtonGroupType.GRID,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewList() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitRadioButtonGroup(
                    items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"),
                    viewType = ToolkitRadioButtonGroupType.LIST,
                )
            }
        }
    }
}