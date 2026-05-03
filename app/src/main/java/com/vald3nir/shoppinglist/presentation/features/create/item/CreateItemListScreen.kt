package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.CreateItemListContentDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.providers.CreateItemListProvider
import com.vald3nir.shoppinglist.presentation.components.AppPreview
import com.vald3nir.shoppinglist.presentation.components.AppTopBar
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitSelectButtonGroup
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun CreateItemListScreen(shoppingListID: String?, viewModel: CreateItemListViewModel = hiltViewModel()) {

    val contentData by viewModel.contentDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(shoppingListID) {
        viewModel.loadScreenContent(shoppingListID)
    }

    ScreenContent(
        contentData = contentData,
        onBackPressed = viewModel::navigateBack,
        onSaveItem = { item ->
            viewModel.insertNewItemList(item.copy(shoppingListId = shoppingListID))
        }
    )
}

@Composable
private fun ScreenContent(
    contentData: CreateItemListContentDTO,
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
            suggestionList = contentData.productNames,
            label = stringResource(R.string.insert_item_list_product_name),
            placeholder = stringResource(R.string.insert_item_list_product),
            leftIcon = ToolkitIconCatalog.ShoppingBasket,
            onValueChange = {
                newItem = newItem.copy(product = it)
            },
        )
        val topProducts = contentData.topProduct
        if (topProducts.isNotEmpty()) {
            ToolkitText(
                modifier = Modifier.padding(ToolkitSpacingMd),
                text = stringResource(R.string.insert_item_top_product_detail),
                style = ToolkitTextStyle.TitleSmall
            )
            ToolkitSelectButtonGroup(
                modifier = Modifier.heightIn(max = 500.dp),
                items = topProducts,
                onItemSelected = { topProduct ->
                    onSaveItem(contentData.selectItemByTopProduct(topProduct))
                }
            )
        }
    }
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(CreateItemListProvider::class) contentData: CreateItemListContentDTO) {
    ToolkitPreviewContainer {
        ScreenContent(contentData)
    }
}