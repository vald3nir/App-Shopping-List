package com.vald3nir.shoppinglist.presentation.features.details.item.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.ui.components.AppTopBar
import com.vald3nir.shoppinglist.core.ui.components.CategorySelector
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.shoppinglist.domain.ItemListDetailDTO
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitAlertDialog
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputInteger
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputMonetary
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitSwitch
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun ItemDetailContent(
    itemDetails: ItemListDetailDTO = ItemListDetailDTO(),
    onUpdateItem: (ItemShoppingListDTO) -> Unit = {},
    onDeleteItem: (itemId: Long?) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    var itemEdited by remember(itemDetails.item) { mutableStateOf(itemDetails.item) }
    var categorySelected by remember(itemDetails.item) {
        mutableStateOf(itemDetails.categories.find { it.name == itemDetails.item.category })
    }
    var showDialog by remember { mutableStateOf(false) }
    ToolkitColumn {
        AppTopBar(
            title = itemEdited.product.orEmpty(),
            onBackPressed = onBackPressed,
            extraIcon = ToolkitIconCatalog.Delete,
            onClickExtraIcon = { showDialog = true }
        )
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = stringResource(R.string.item_details_title),
            style = ToolkitTextStyle.TitleSmall
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .imePadding()
        ) {
            ToolkitAutoCompleteInput(
                inputValue = itemEdited.product.orEmpty(),
                suggestionList = itemDetails.productNames,
                label = stringResource(R.string.insert_item_list_product_name),
                placeholder = stringResource(R.string.insert_item_list_product),
                leftIcon = ToolkitIconCatalog.ShoppingBasket,
                onValueChange = {
                    itemEdited = itemEdited.copy(product = it)
                },
            )
            CategorySelector(
                categories = itemDetails.categories,
                selectedOption = categorySelected?.name,
                onOptionSelected = { categorySelected = it }
            )
            ToolkitInputMonetary(
                inputValue = itemEdited.unitPrice,
                label = stringResource(R.string.insert_item_list_unit_price_label),
                placeholder = stringResource(R.string.insert_item_list_unit_price_description),
                leftIcon = ToolkitIconCatalog.AttachMoney,
                onValueChange = {
                    itemEdited = itemEdited.copy(unitPrice = it)
                },
            )
            ToolkitInputInteger(
                inputValue = itemEdited.quantity,
                label = stringResource(R.string.insert_item_list_products_size_label),
                placeholder = stringResource(R.string.insert_item_list_products_size_description),
                leftIcon = ToolkitIconCatalog.Pin,
                onValueChange = {
                    itemEdited = itemEdited.copy(quantity = it)
                },
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ToolkitText(
                    modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
                    text = stringResource(R.string.item_details_keep_on_card_question),
                    style = ToolkitTextStyle.TitleSmall
                )
                ToolkitSwitch(startEnable = itemEdited.isAdd, onCheckedChange = { itemEdited = itemEdited.copy(isAdd = it) })
            }
        }
        if (showDialog) {
            val deleteMessage = stringResource(R.string.item_details_remove_item_description, itemEdited.product.orEmpty())
            ToolkitAlertDialog(
                title = stringResource(R.string.item_details_remove_item_confirm),
                description = deleteMessage,
                btnConfirmLabel = stringResource(R.string.remove),
                btnCancelLabel = stringResource(R.string.cancel),
                onConfirm = {
                    onDeleteItem(itemEdited.id)
                    showDialog = false
                },
                onCancel = { showDialog = false }
            )
        }
        ToolkitFixedButton(
            label = stringResource(R.string.item_details_btn_save),
            enabled = itemEdited.isValid(),
            onClick = {
                onUpdateItem(
                    itemEdited.copy(
                        category = categorySelected?.name,
                        iconURL = categorySelected?.iconURL
                    )
                )
            }
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground {
            ItemDetailContent()
        }
    }
}