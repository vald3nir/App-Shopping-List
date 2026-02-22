package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.presentation.features.profile.ui.ProfileContent
import com.vald3nir.shoppinglist.presentation.features.profile.ui.ProfileNotAuthContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.orFalse
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitAlertDialog
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.themas.presentation.SelectThemeDialog

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    redirectToAuth: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val profileData by viewModel.profileDataFlow.collectAsStateWithLifecycle()
    if (uiState is BaseUiState.LoadingState) {
        ToolkitLoadingFullscreen()
        return
    }

    var showLogoutDialog by remember { mutableStateOf(false) }
    var showAlterThemeDialog by remember { mutableStateOf(false) }

    if (profileData.user?.isAuthenticated().orFalse()) {
        ProfileContent(
            profileData = profileData,
            onClickLogout = { showLogoutDialog = true },
            onClickAlterTheme = { showAlterThemeDialog = true },
            onBackPressed = viewModel::navigateBack
        )
    } else {
        ProfileNotAuthContent(
            profileData = profileData,
            onClickLogin = redirectToAuth,
            onClickAlterTheme = { showAlterThemeDialog = true },
            onBackPressed = viewModel::navigateBack
        )
    }

    if (showLogoutDialog) {
        ToolkitAlertDialog(
            title = stringResource(R.string.profile_dialog_logout_title),
            description = stringResource(R.string.profile_dialog_logout_description),
            btnConfirmLabel = stringResource(R.string.logout),
            btnCancelLabel = stringResource(R.string.cancel),
            onConfirm = {
                viewModel.logout()
                showLogoutDialog = false
            },
            onCancel = { showLogoutDialog = false }
        )
        return
    }

    if (showAlterThemeDialog) {
        SelectThemeDialog(onDismiss = { showAlterThemeDialog = false })
    }
}