package com.vald3nir.shoppinglist.presentation.features.create.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
private data object CreateListRoute

fun NavController.navigateToCreateList() {
    this.navigate(CreateListRoute)
}

fun NavGraphBuilder.setupCreateListRoute(
    onClickAddData: (shoppingListID: Long?) -> Unit,
    onBackPressed: () -> Unit
) {
    composable<CreateListRoute> {
        CreateListScreen(onClickAddData = onClickAddData, onBackPressed = onBackPressed)
    }
}