package com.vald3nir.shoppinglist.presentation.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object HomeRoute

internal fun NavGraphBuilder.setupHomeRoute(
    redirectToCreateList: () -> Unit,
    redirectToListDetail: (shoppingListID: Long?) -> Unit,
    redirectToProfile: () -> Unit,
    redirectToAuth: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            redirectToCreateList = redirectToCreateList,
            redirectToListDetail = redirectToListDetail,
            redirectToProfile = redirectToProfile,
            redirectToAuth = redirectToAuth,
        )
    }
}