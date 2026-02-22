package com.vald3nir.shoppinglist.presentation.features.details.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
private data class ListDetailsRouter(val shoppingListID: Long?)

fun NavController.navigateToListDetails(shoppingListID: Long?) {
    this.navigate(ListDetailsRouter(shoppingListID = shoppingListID))
}

internal fun NavGraphBuilder.setupListDetailsRoute(
    onClickAddData: (shoppingListID: Long?) -> Unit,
    onClickItemDetail: (itemId: Long?) -> Unit
) {
    composable<ListDetailsRouter> { backStackEntry ->
        val route = backStackEntry.toRoute<ListDetailsRouter>()
        ListDetailsScreen(
            shoppingListID = route.shoppingListID,
            onClickAddData = onClickAddData,
            onClickItemDetail = onClickItemDetail,
        )
    }
}