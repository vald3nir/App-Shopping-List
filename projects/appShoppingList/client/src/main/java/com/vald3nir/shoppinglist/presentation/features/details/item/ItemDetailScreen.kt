package com.vald3nir.shoppinglist.presentation.features.details.item

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.toolkit.core.baseclasses.BaseUiState

@Composable
internal fun ItemDetailScreen(
    itemId: Long?,
    viewModel: ItemDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val itemDetails by viewModel.itemDetailsDataFlow.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message by viewModel.uiMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(itemId) {
        viewModel.loadItemShoppingList(itemId)
    }

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

    ItemDetailScreenContent(
        itemDetails = itemDetails,
        snackBarHostState = snackBarHostState,
        onBackPressed = onBackPressed,
        onUpdateItem = viewModel::updateItem,
        onDeleteItem = viewModel::deleteItem,
    )
}