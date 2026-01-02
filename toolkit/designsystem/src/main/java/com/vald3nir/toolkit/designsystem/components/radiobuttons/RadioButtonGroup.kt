package com.vald3nir.toolkit.designsystem.components.radiobuttons

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

enum class ToolkitRadioButtonGroupType { LIST, GRID }

@Composable
fun ToolkitRadioButtonGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedValue: String? = null,
    viewType: ToolkitRadioButtonGroupType,
    columnsSize: Int = 2, // for grid
    onItemSelected: (String) -> Unit = {},
) {
    var selectedItem by remember { mutableStateOf(selectedValue) }

    when (viewType) {
        ToolkitRadioButtonGroupType.LIST -> {
            LazyColumn(modifier = modifier) {
                items.forEach { item ->
                    item {
                        ToolkitRadioButton(
                            label = item,
                            checked = selectedItem == item,
                            onClick = {
                                selectedItem = item
                                onItemSelected(item)
                            }
                        )
                    }
                }
            }
        }

        ToolkitRadioButtonGroupType.GRID -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnsSize),
                modifier = modifier
            ) {
                items.forEach { item ->
                    item {
                        ToolkitRadioButton(
                            label = item,
                            checked = selectedItem == item,
                            onClick = {
                                selectedItem = item
                                onItemSelected(item)
                            }
                        )
                    }
                }
            }
        }
    }
}