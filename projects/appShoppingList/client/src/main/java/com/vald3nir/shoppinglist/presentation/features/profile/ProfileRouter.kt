package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object ProfileRouter

fun NavController.navigateToProfile() {
    this.navigate(ProfileRouter)
}

internal fun NavGraphBuilder.setupProfileRoute() {
    composable<ProfileRouter> { ProfileScreen() }
}