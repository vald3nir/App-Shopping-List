package com.vald3nir.shoppinglist.presentation.features.create.item.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.ui.components.AppTopBar
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputInteger
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputMonetary
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun CreateItemListScreenContent(
    productsName: List<String> = emptyList(),
    onSaveItem: (ItemShoppingListDTO) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    var newItem: ItemShoppingListDTO by remember { mutableStateOf(ItemShoppingListDTO()) }
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.insert_item_list_title),
            onBackPressed = onBackPressed,
            extraIcon = if (newItem.isValid()) ToolkitIconCatalog.Save else null,
            onClickExtraIcon = { onSaveItem(newItem) }
        )
        ToolkitText(
            modifier = Modifier.padding(start = ToolkitSpacingMd, top = ToolkitSpacingMd, end = ToolkitSpacingMd),
            text = stringResource(R.string.insert_item_product_detail),
            style = ToolkitTextStyle.TitleSmall
        )
        ToolkitAutoCompleteInput(
            inputValue = newItem.product.orEmpty(),
            suggestionList = productsName,
            label = stringResource(R.string.insert_item_list_product_name),
            placeholder = stringResource(R.string.insert_item_list_product),
            leftIcon = ToolkitIconCatalog.ShoppingBasket,
            onValueChange = {
                newItem = newItem.copy(product = it)
            },
        )
        ToolkitText(
            modifier = Modifier.padding(start = ToolkitSpacingMd, top = ToolkitSpacingXl, end = ToolkitSpacingMd),
            text = stringResource(R.string.insert_item_total_amount_detail),
            style = ToolkitTextStyle.TitleSmall
        )
        ToolkitInputMonetary(
            inputValue = newItem.unitPrice,
            label = stringResource(R.string.insert_item_list_unit_price_label),
            placeholder = stringResource(R.string.insert_item_list_unit_price_description),
            leftIcon = ToolkitIconCatalog.AttachMoney,
            onValueChange = {
                newItem = newItem.copy(unitPrice = it)
            },
        )
        ToolkitInputInteger(
            inputValue = newItem.quantity,
            label = stringResource(R.string.insert_item_list_products_size_label),
            placeholder = stringResource(R.string.insert_item_list_products_size_description),
            leftIcon = ToolkitIconCatalog.Pin,
            onValueChange = {
                newItem = newItem.copy(quantity = it)
            },
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    AppTheme {
        ToolkitBackground {
            CreateItemListScreenContent()
        }
    }
}