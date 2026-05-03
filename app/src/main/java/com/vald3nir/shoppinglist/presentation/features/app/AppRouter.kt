package com.vald3nir.shoppinglist.presentation.features.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.presentation.features.create.item.CreateItemListScreen
import com.vald3nir.shoppinglist.presentation.features.create.list.CreateListScreen
import com.vald3nir.shoppinglist.presentation.features.details.addItem.AddItemListScreen
import com.vald3nir.shoppinglist.presentation.features.details.edititem.EditItemListScreen
import com.vald3nir.shoppinglist.presentation.features.details.list.ListDetailsScreen
import com.vald3nir.shoppinglist.presentation.features.home.HomeScreen
import com.vald3nir.shoppinglist.presentation.features.profile.ProfileScreen
import com.vald3nir.toolkit.auth.presentation.navigateToAuth
import com.vald3nir.toolkit.auth.presentation.setupAuthRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ------------------------------------------------------------------------------------------------------------
// Routers / Paths
// ------------------------------------------------------------------------------------------------------------
internal sealed class AppRoutes {

    @Serializable
    @SerialName("home")
    data object HomeRoute : AppRoutes()

    @Serializable
    @SerialName("profile")
    data object ProfileRoute : AppRoutes()

    @Serializable
    @SerialName("create_list")
    data object CreateListRoute : AppRoutes()

    @Serializable
    @SerialName("list_detail")
    data class ListDetailsRouter(val shoppingListID: String?) : AppRoutes()

    @Serializable
    @SerialName("create_item_list")
    data class CreateItemListRoute(val shoppingListID: String?) : AppRoutes()

    @Serializable
    @SerialName("add_item_list")
    data class AddItemListRoute(val shoppingListID: String?) : AppRoutes()

    @Serializable
    @SerialName("edit_item_list")
    data class EditItemListRoute(val itemId: String?) : AppRoutes()


}

// ------------------------------------------------------------------------------------------------------------
// Extensions
// ------------------------------------------------------------------------------------------------------------

private fun NavController.navigateToProfile() {
    this.navigate(AppRoutes.ProfileRoute)
}

private fun NavController.navigateToCreateList() {
    this.navigate(AppRoutes.CreateListRoute)
}

private fun NavController.navigateToCreateItemList(shoppingListID: String?) {
    this.navigate(AppRoutes.CreateItemListRoute(shoppingListID = shoppingListID))
}

private fun NavController.navigateToAddItemList(shoppingListID: String?) {
    this.navigate(AppRoutes.AddItemListRoute(shoppingListID = shoppingListID))
}

private fun NavController.navigateToListDetails(shoppingListID: String?) {
    this.navigate(AppRoutes.ListDetailsRouter(shoppingListID = shoppingListID))
}

private fun NavController.navigateToItemDetail(itemId: String?) {
    this.navigate(AppRoutes.EditItemListRoute(itemId = itemId))
}

// ------------------------------------------------------------------------------------------------------------
// Graph Routes
// ------------------------------------------------------------------------------------------------------------
@Composable
internal fun AppRouter(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppRoutes.HomeRoute, modifier = modifier) {

        composable<AppRoutes.HomeRoute> {
            HomeScreen(
                redirectToListDetail = { navController.navigateToListDetails(shoppingListID = it) },
                redirectToCreateList = { navController.navigateToCreateList() },
                redirectToProfile = { navController.navigateToProfile() },
            )
        }

        composable<AppRoutes.ProfileRoute> {
            ProfileScreen(redirectToAuth = { navController.navigateToAuth() })
        }

        setupAuthRoute(
            appPrivacyPolicyURL = BuildConfig.APP_PRIVACY_POLICY_URL,
            appTermsUseLink = BuildConfig.APP_TERMS_USE_URL,
            webGoogleClientID = BuildConfig.WEB_GOOGLE_CLIENT_ID,
        )

        composable<AppRoutes.CreateListRoute> {
            CreateListScreen(onClickAddData = {
                navController.navigateToCreateItemList(shoppingListID = it)
            })
        }

        composable<AppRoutes.ListDetailsRouter> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.ListDetailsRouter>()
            ListDetailsScreen(
                shoppingListID = route.shoppingListID,
                onClickAddData = { shoppingListID ->
                    navController.navigateToAddItemList(shoppingListID = shoppingListID)
                },
                onClickItemDetail = { itemId ->
                    navController.navigateToItemDetail(itemId)
                }
            )
        }

        composable<AppRoutes.CreateItemListRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.CreateItemListRoute>()
            CreateItemListScreen(shoppingListID = route.shoppingListID)
        }

        composable<AppRoutes.AddItemListRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.AddItemListRoute>()
            AddItemListScreen(shoppingListID = route.shoppingListID)
        }

        composable<AppRoutes.EditItemListRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.EditItemListRoute>()
            EditItemListScreen(itemId = route.itemId)
        }
    }
}