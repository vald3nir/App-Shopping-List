package com.vald3nir.shoppinglist.presentation.features.details.list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.shoppinglist.domain.providers.ItemsShoppingListProvider
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.HalfSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitCheckBox
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.defaultSpace
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.image.ToolkitAsyncImage
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun ItemListDetailsRow(
    modifier: Modifier = Modifier,
    item: ItemShoppingListDTO,
    showDivider: Boolean = false,
    onClickItemDetail: (itemId: Long?) -> Unit = {},
    onChangeItemStatus: (id: Long?) -> Unit = {}
) {
    Column(modifier = modifier.clickable { onClickItemDetail(item.id) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = defaultSpace),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HalfSpaceWidth()
            if (item.iconURL.isNullOrEmpty()) {
                ToolkitIcon(
                    modifier = Modifier.size(28.dp),
                    imageVector = ToolkitIconCatalog.ShoppingBasket,
                )
            } else {
                ToolkitAsyncImage(
                    modifier = Modifier.size(28.dp),
                    imageUrl = item.iconURL
                )
            }
            DefaultSpaceWidth()
            Column(modifier = Modifier.weight(1f)) {
                ToolkitText(text = item.description(), style = ToolkitTextStyle.TitleMedium)
                item.category?.let { ToolkitText(text = it, style = ToolkitTextStyle.LabelLarge) }
            }
            ToolkitCheckBox(
                checked = item.isAdd,
                onCheckedChange = { onChangeItemStatus(item.id) },
            )
        }
        AnimatedVisibility(visible = showDivider) {
            ToolkitDivider()
        }
    }
}

@ThemePreviews
@Composable
private fun Preview(
    @PreviewParameter(ItemsShoppingListProvider::class)
    items: List<ItemShoppingListDTO>,
) {
    AppTheme {
        ToolkitBackground {
            LazyColumn {
                itemsIndexed(items = items, itemContent = { index, item ->
                    ItemListDetailsRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = defaultSpace),
                        item = item,
                        showDivider = index != items.lastIndex
                    )
                })
            }
        }
    }
}