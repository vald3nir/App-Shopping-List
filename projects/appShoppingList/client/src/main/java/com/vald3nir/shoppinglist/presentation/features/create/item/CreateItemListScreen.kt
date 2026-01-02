package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.presentation.components.buildTopBar
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputInteger
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputMonetary
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.templates.ToolkitBaseContent

@Composable
internal fun CreateItemListScreen(
    shoppingListID: Long?,
    viewModel: CreateItemListViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message by viewModel.uiMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val productNames by viewModel.productNames.collectAsStateWithLifecycle()

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            snackBarHostState.showSnackbar(message = message, duration = SnackbarDuration.Short)
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is BaseUiState.CloseState) {
            onBackPressed()
        }
    }

    CreateItemListScreenContent(
        snackBarHostState = snackBarHostState,
        productsName = productNames,
        onBackPressed = onBackPressed,
        onSaveItem = { item ->
            viewModel.insertNewItemShoppingList(item.copy(shoppingListId = shoppingListID))
        }
    )
}

@Composable
private fun CreateItemListScreenContent(
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    productsName: List<String> = emptyList(),
    onSaveItem: (ItemShoppingListDTO) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    var newItem: ItemShoppingListDTO by remember { mutableStateOf(ItemShoppingListDTO()) }

    ToolkitBaseContent(
        snackBarHostState = snackBarHostState,
        topBar = buildTopBar(
            title = stringResource(R.string.insert_item_list_title),
            onBackPressed = onBackPressed,
        ),
        content = {
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
        },
        bottomBar = {
            Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical))) {
                ToolkitFixedButton(
                    label = stringResource(R.string.insert_item_btn_save),
                    enabled = newItem.isValid(),
                    onClick = { onSaveItem(newItem) }
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground {
            CreateItemListScreenContent()
        }
    }
}