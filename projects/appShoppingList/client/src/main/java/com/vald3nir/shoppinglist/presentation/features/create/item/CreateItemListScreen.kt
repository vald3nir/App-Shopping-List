package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.features.create.item.ui.CreateItemListScreenContent

@Composable
internal fun CreateItemListScreen(shoppingListID: Long?, viewModel: CreateItemListViewModel = hiltViewModel()) {
    val productNames by viewModel.productNames.collectAsStateWithLifecycle()
    CreateItemListScreenContent(
        productsName = productNames,
        onBackPressed = viewModel::navigateBack,
        onSaveItem = { item ->
            viewModel.insertNewItemList(item.copy(shoppingListId = shoppingListID))
        }
    )
}