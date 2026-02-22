package com.vald3nir.shoppinglist.presentation.features.details.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.features.details.item.ui.ItemDetailContent

@Composable
internal fun ItemDetailScreen(itemId: Long?, viewModel: ItemDetailViewModel = hiltViewModel()) {
    val itemDetails by viewModel.itemDetailsDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(itemId) {
        viewModel.loadItemShoppingList(itemId)
    }

    ItemDetailContent(
        itemDetails = itemDetails,
        onBackPressed = viewModel::navigateBack,
        onUpdateItem = viewModel::updateItem,
        onDeleteItem = viewModel::deleteItem,
    )
}