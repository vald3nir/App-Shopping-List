package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitTextButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSelectButtonGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    onItemSelected: (String) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(horizontal = ToolkitSpacingMd),
        horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingSm), // Espaço entre as colunas
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingSm)     // Espaço entre as linhas
    ) {
        items.forEach { item ->
            item {
                ToolkitTextButton(
                    label = item,
                    trailingIcon = ToolkitIconCatalog.Add,
                    onClick = {
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ToolkitSelectButtonGroup(
            items = listOf("Item 1", "Item 2", "Item 3", "Item 4"),
        )
    }
}