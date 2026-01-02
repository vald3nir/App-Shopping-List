package com.vald3nir.shoppinglist.presentation.features.appscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vald3nir.toolkit.core.services.threads.TrackDisposableJank
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppScreenState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
    ) {
        AppScreenState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class AppScreenState(val navController: NavHostController, coroutineScope: CoroutineScope) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    var shouldShowSettingsDialog by mutableStateOf(false)
        private set

    fun setShowSettingsDialog(shouldShow: Boolean) {
        shouldShowSettingsDialog = shouldShow
    }


}

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}