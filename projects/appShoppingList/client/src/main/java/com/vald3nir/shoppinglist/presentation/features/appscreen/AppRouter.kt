package com.vald3nir.shoppinglist.presentation.features.appscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.presentation.features.create.item.navigateToCreateItemList
import com.vald3nir.shoppinglist.presentation.features.create.item.setupCreateItemListRoute
import com.vald3nir.shoppinglist.presentation.features.create.list.navigateToCreateList
import com.vald3nir.shoppinglist.presentation.features.create.list.setupCreateListRoute
import com.vald3nir.shoppinglist.presentation.features.details.item.navigateToItemDetail
import com.vald3nir.shoppinglist.presentation.features.details.item.setupItemDetailRoute
import com.vald3nir.shoppinglist.presentation.features.details.list.navigateToListDetails
import com.vald3nir.shoppinglist.presentation.features.details.list.setupListDetailsRoute
import com.vald3nir.shoppinglist.presentation.features.home.HomeRoute
import com.vald3nir.shoppinglist.presentation.features.home.setupHomeRoute
import com.vald3nir.shoppinglist.presentation.features.profile.navigateToProfile
import com.vald3nir.shoppinglist.presentation.features.profile.setupProfileRoute
import com.vald3nir.toolkit.auth.presentation.navigateToAuth
import com.vald3nir.toolkit.auth.presentation.setupAuthRoute

@Composable
internal fun AppRouter(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeRoute, modifier = modifier) {

        setupProfileRoute(redirectToAuth = {
            navController.navigateToAuth()
        })

        setupHomeRoute(
            redirectToListDetail = { shoppingListID ->
                navController.navigateToListDetails(shoppingListID = shoppingListID)
            },
            redirectToCreateList = {
                navController.navigateToCreateList()
            },
            redirectToProfile = {
                navController.navigateToProfile()
            },
        )

        setupAuthRoute(
            appPrivacyPolicyURL = BuildConfig.APP_PRIVACY_POLICY_URL,
            appTermsUseLink = BuildConfig.APP_TERMS_USE_URL,
            webGoogleClientID = BuildConfig.WEB_GOOGLE_CLIENT_ID,
        )

        setupCreateListRoute(
            onClickAddData = { shoppingListID ->
                navController.navigateToCreateItemList(shoppingListID = shoppingListID)
            },
        )

        setupCreateItemListRoute()

        setupListDetailsRoute(
            onClickAddData = { shoppingListID ->
                navController.navigateToCreateItemList(shoppingListID = shoppingListID)
            },
            onClickItemDetail = { itemId ->
                navController.navigateToItemDetail(itemId)
            }
        )

        setupItemDetailRoute()
    }
}