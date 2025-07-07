package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home

import android.content.Context
import androidx.navigation.NavController
import com.vald3nir.shoppinglist.presentation.features.boot.buildBootActivityIntent
import com.vald3nir.shoppinglist.presentation.features.shoppingList.navigation.ShoppingListScreenRoute
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

data class HomeScreenScope(
    val viewModel: HomeViewModel,
    val navController: NavController
) : BaseScreenScope(viewModel, navController) {

    val updateSearchQueryEvent = viewModel::updateSearchQuery

    fun redirectToShowList(shoppingListID: Long?) {
        viewModel.emitNavigationEvent(ShoppingListScreenRoute.ShowList(shoppingListID))
    }

    fun redirectToCreateNewList() {
        viewModel.emitNavigationEvent(ShoppingListScreenRoute.NewList)
    }

    fun onChangeUser(context: Context) {
        if (isUserLogged()) {
            viewModel.userLogout()
        }
        context.apply { startActivity(buildBootActivityIntent()) }
    }

    fun isUserLogged() = viewModel.isUserLogged()
    fun userPhotoUrl() = viewModel.getUserLogged()?.photoUrl
}