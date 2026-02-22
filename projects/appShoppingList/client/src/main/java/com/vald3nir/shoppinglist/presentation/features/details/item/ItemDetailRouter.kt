package com.vald3nir.shoppinglist.presentation.features.details.item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
private data class ItemDetailRouter(val itemId: Long?)

fun NavController.navigateToItemDetail(itemId: Long?) {
    this.navigate(ItemDetailRouter(itemId = itemId))
}

internal fun NavGraphBuilder.setupItemDetailRoute() {
    composable<ItemDetailRouter> { backStackEntry ->
        val route = backStackEntry.toRoute<ItemDetailRouter>()
        ItemDetailScreen(itemId = route.itemId)
    }
}