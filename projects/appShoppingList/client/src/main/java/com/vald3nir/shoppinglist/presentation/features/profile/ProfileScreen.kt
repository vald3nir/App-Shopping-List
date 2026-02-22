package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.features.profile.ui.ProfileContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val profileData by viewModel.profileDataFlow.collectAsStateWithLifecycle()
    if (uiState is BaseUiState.LoadingState) {
        ToolkitLoadingFullscreen()
        return
    }
    ProfileContent(
        profileData = profileData,
        onClickLogout = viewModel::logout,
        onChangeThemeBrand = viewModel::updateThemeBrand,
        onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
        onBackPressed = viewModel::navigateBack
    )
}