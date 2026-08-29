package com.vald3nir.shoppinglist.presentation.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.providers.ItemsShoppingListProvider
import com.vald3nir.toolkit.core.utils.extensions.toMoney
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitDeleteButton
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitCheckBox
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun SelectItemListRow(
    modifier: Modifier = Modifier,
    item: ItemShoppingListDTO,
    showDivider: Boolean = false,
    onClickItemDetail: (itemId: String?) -> Unit = {},
    onChangeItemStatus: (id: String?) -> Unit = {},
    onDeleteItem: (itemId: String?) -> Unit = {},
) {
    var confirmState by remember { mutableStateOf(false) }
    Column(modifier = modifier.clickable { onClickItemDetail(item.id) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = ToolkitSpacingMd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToolkitSpaceWidth(ToolkitSpacingXs)
            ToolkitCheckBox(
                checked = item.onCart,
                onCheckedChange = { onChangeItemStatus(item.id) },
            )
            ToolkitSpaceWidth()
            Column(modifier = Modifier.weight(1f)) {
                ToolkitText(text = "${item.product.orEmpty()} - ${item.quantity} Uni.", style = ToolkitTextStyle.TitleMedium)
                if (item.unitPrice > 0) {
                    ToolkitText(text = item.unitPrice.toMoney(), style = ToolkitTextStyle.LabelMedium)
                }
            }
            ToolkitDeleteButton(
                confirmState = confirmState,
                onClickRemove = {
                    onDeleteItem(item.id)
                    confirmState = false
                },
                onClickCancel = { confirmState = false },
                onClickShowConfirm = { confirmState = true },
            )
        }
        AnimatedVisibility(visible = showDivider) {
            ToolkitDivider()
        }
    }
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(ItemsShoppingListProvider::class) items: List<ItemShoppingListDTO>) {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 250.dp)) {
        LazyColumn {
            itemsIndexed(items) { index, item ->
                SelectItemListRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ToolkitSpacingMd),
                    item = item,
                    showDivider = index != items.lastIndex
                )
            }
        }
    }
}