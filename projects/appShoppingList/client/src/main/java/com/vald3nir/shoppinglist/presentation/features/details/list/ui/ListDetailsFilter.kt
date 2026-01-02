package com.vald3nir.shoppinglist.presentation.features.details.list.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListTabDTO
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.tabs.ToolkitTab
import com.vald3nir.toolkit.designsystem.components.tabs.ToolkitTabRow

@Composable
internal fun ListDetailsFilter(
    showItemsOnCart: () -> Unit = {},
    showItemsOffCart: () -> Unit = {},
) {
    var selectedTabIndex: Int by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        ShoppingListTabDTO(
            title = stringResource(R.string.list_details_add_to_shopping_cart),
            icon = ToolkitIconCatalog.Add,
            action = showItemsOffCart,
        ),
        ShoppingListTabDTO(
            title = stringResource(R.string.list_details_remove_from_shopping_cart),
            icon = ToolkitIconCatalog.Remove,
            action = showItemsOnCart,
        ),
    )
    ToolkitTabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, tab ->
            ToolkitTab(
                selected = selectedTabIndex == index,
                text = tab.title,
                icon = tab.icon,
                onClick = {
                    selectedTabIndex = index
                    tab.action()
                },
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground(modifier = Modifier.size(500.dp, 100.dp)) {
            ListDetailsFilter()
        }
    }
}