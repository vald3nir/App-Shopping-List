package com.vald3nir.shoppinglist.presentation.components

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
import com.vald3nir.shoppinglist.domain.dto.ShoppingListTabDTO
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitTab
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitTabRow
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun ShoppingCartFilter(
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

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 100.dp)) {
        ShoppingCartFilter()
    }
}