package com.vald3nir.shoppinglist.presentation.features.details.addItem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ScreenItemListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.presentation.components.AppPreview
import com.vald3nir.shoppinglist.presentation.components.AppTopBar
import com.vald3nir.toolkit.camera.BarcodeAnalyzer
import com.vald3nir.toolkit.camera.CameraScannerScreen
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitTextButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputInteger
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputMonetary
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun AddItemListScreen(shoppingListID: String?, viewModel: AddItemListViewModel = hiltViewModel()) {

    val screenData by viewModel.screenDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(shoppingListID) {
        viewModel.shoppingListId = shoppingListID
    }

    Content(
        screenData = screenData,
        onBackPressed = viewModel::navigateBack,
        onUpdateItem = viewModel::updateItem,
        onSaveItem = viewModel::onSaveItem,
        searchProductNameByBarCode = viewModel::searchProductNameByBarCode,
    )
}

@Composable
private fun Content(
    screenData: ScreenItemListDTO = ScreenItemListDTO(),
    onUpdateItem: (ItemShoppingListDTO) -> Unit = {},
    searchProductNameByBarCode: (barCode: String?) -> Unit = {},
    onBackPressed: () -> Unit = {},
    onSaveItem: () -> Unit = {},
) {
    var showBarCodeView by remember { mutableStateOf(false) }

    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.insert_item_title),
            onBackPressed = onBackPressed,
            extraIcon = if (screenData.isValid) ToolkitIconCatalog.Save else null,
            onClickExtraIcon = onSaveItem
        )

        ToolkitSpaceHeight()
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = stringResource(R.string.insert_item_description),
            style = ToolkitTextStyle.TitleSmall
        )


        ToolkitAutoCompleteInput(
            inputValue = screenData.currentProduct,
            suggestionList = screenData.productNames,
            label = stringResource(R.string.insert_item_list_product_name),
            placeholder = stringResource(R.string.insert_item_list_product),
            leftIcon = ToolkitIconCatalog.ShoppingBasket,
            onValueChange = {
                onUpdateItem(screenData.item.copy(product = it))
            },
        )

        ToolkitTextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ToolkitSpacingMd),
            onClick = { showBarCodeView = true },
            label = stringResource(R.string.insert_item_btn_barcode),
            trailingIcon = ToolkitIconCatalog.QrCodeScanner,
        )

        ToolkitInputMonetary(
            inputValue = screenData.item.unitPrice,
            label = stringResource(R.string.insert_item_list_unit_price_label),
            placeholder = stringResource(R.string.insert_item_list_unit_price_description),
            leftIcon = ToolkitIconCatalog.AttachMoney,
            onValueChange = {
                onUpdateItem(screenData.item.copy(unitPrice = it))
            },
        )
        ToolkitInputInteger(
            inputValue = screenData.item.quantity,
            label = stringResource(R.string.insert_item_list_products_size_label),
            placeholder = stringResource(R.string.insert_item_list_products_size_description),
            leftIcon = ToolkitIconCatalog.Pin,
            onValueChange = {
                onUpdateItem(screenData.item.copy(quantity = it))
            },
        )
    }

    if (showBarCodeView) {
        CameraScannerScreen(BarcodeAnalyzer { barCode ->
            showBarCodeView = false
            searchProductNameByBarCode(barCode)
        })
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        Content()
    }
}