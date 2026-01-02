package com.vald3nir.shoppinglist.presentation.features.appscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitGradientBackground
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors

@Composable
internal fun AppScreen(appState: AppScreenState, modifier: Modifier = Modifier) {
    ToolkitBackground(modifier = modifier) {
        ToolkitGradientBackground(gradientColors = LocalGradientColors.current) {
            AppScreenContent(appState = appState)
        }
    }
}

@Composable
private fun AppScreenContent(appState: AppScreenState) {
    AppRouter(
        appState = appState,
//        onShowSnackBar = { message, action ->
//            snackBarHostState.showSnackbar(
//                message = message,
//                actionLabel = action,
//                duration = SnackbarDuration.Short,
//            ) == ActionPerformed
//        },
    )
}