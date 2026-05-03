package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.providers.ItemsShoppingListProvider
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.HalfSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.defaultSpace
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun CreateItemListRow(
    modifier: Modifier = Modifier,
    item: ItemShoppingListDTO,
    showDivider: Boolean = false,
    onClickRemove: (id: String?) -> Unit
) {
    var showDeleteItemDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.clickable { showDeleteItemDialog = true }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = defaultSpace),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HalfSpaceWidth()
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.ic_groceries),
                contentDescription = null
            )
            DefaultSpaceWidth()
            ToolkitText(modifier = Modifier.weight(1f), text = item.product.orEmpty(), style = ToolkitTextStyle.TitleMedium)
            ToolkitIcon(
                modifier = Modifier.size(28.dp),
                imageVector = ToolkitIconCatalog.Delete,
                tint = Color.Red
            )
        }
        AnimatedVisibility(visible = showDivider) {
            ToolkitDivider()
        }
    }

    if (showDeleteItemDialog) {
        ShowDeleteItemListDialog(
            itemName = item.product.orEmpty(),
            onConfirm = {
                onClickRemove(item.id)
                showDeleteItemDialog = false
            },
            onCancel = { showDeleteItemDialog = false }
        )
    }
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(ItemsShoppingListProvider::class) items: List<ItemShoppingListDTO>) {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 250.dp)) {
        LazyColumn {
            itemsIndexed(items) { index, item ->
                CreateItemListRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ToolkitSpacingMd),
                    item = item,
                    showDivider = index != items.lastIndex,
                    onClickRemove = {},
                )
            }
        }
    }
}