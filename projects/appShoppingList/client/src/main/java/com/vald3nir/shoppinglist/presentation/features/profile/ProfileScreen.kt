package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.components.ScreenLoading
import com.vald3nir.shoppinglist.presentation.features.profile.ui.ProfileScreenContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message by viewModel.uiMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val profileData by viewModel.profileDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is BaseUiState.CloseState) {
            onBackPressed()
        }
    }

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            snackBarHostState.showSnackbar(message = message, duration = SnackbarDuration.Short)
        }
    }

    if (uiState is BaseUiState.LoadingState) {
        ScreenLoading()
        return
    }

    ProfileScreenContent(
        profileData = profileData,
        snackBarHostState = snackBarHostState,
        onClickLogout = viewModel::logout,
        onChangeThemeBrand = viewModel::updateThemeBrand,
        onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
        onBackPressed = onBackPressed
    )
}