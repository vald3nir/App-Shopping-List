package com.vald3nir.shoppinglist.presentation.features.create.item

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
private data class CreateItemListRouter(val shoppingListID: Long?)

fun NavController.navigateToCreateItemList(shoppingListID: Long?) {
    this.navigate(CreateItemListRouter(shoppingListID = shoppingListID))
}

internal fun NavGraphBuilder.setupCreateItemListRoute() {
    composable<CreateItemListRouter> { backStackEntry ->
        val route = backStackEntry.toRoute<CreateItemListRouter>()
        CreateItemListScreen(shoppingListID = route.shoppingListID)
    }
}