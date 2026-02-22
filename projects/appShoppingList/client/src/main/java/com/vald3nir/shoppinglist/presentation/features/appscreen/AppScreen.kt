package com.vald3nir.shoppinglist.presentation.features.appscreen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitGradientBackground
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors

@Composable
internal fun AppScreen(modifier: Modifier = Modifier) {
    ToolkitBackground(modifier = modifier) {
        ToolkitGradientBackground(gradientColors = LocalGradientColors.current) {
            AppScreenContent()
        }
    }
}

@Composable
private fun AppScreenContent(viewModel: AppScreenViewModel = hiltViewModel()) {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController: NavHostController = rememberNavController()

    LaunchedEffect(Unit) {
        viewModel.messageObserver().collect { message ->
            if (message.isNotEmpty()) {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigateObserver().collect {
            navController.popBackStack()
        }
    }

    ToolkitScaffold(snackBarHostState = snackBarHostState) {
        AppRouter(navController = navController)
    }
}