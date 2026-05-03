package com.vald3nir.shoppinglist.presentation.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.presentation.components.AppPreview
import com.vald3nir.shoppinglist.presentation.components.AppTopBar
import com.vald3nir.shoppinglist.presentation.components.ShowLogoutDialog
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.theme.presentation.SelectThemeDialog
import com.vald3nir.toolkit.core.utils.extensions.orFalse
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitOutlinedButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitAppVersionContent
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitProfileContent

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    redirectToAuth: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userDataFlow by viewModel.userDataFlow.collectAsStateWithLifecycle()
    if (uiState is BaseUiState.LoadingState) {
        ToolkitLoadingFullscreen()
        return
    }

    var showLogoutDialog by remember { mutableStateOf(false) }
    var showAlterThemeDialog by remember { mutableStateOf(false) }

    if (userDataFlow.isAuthenticated().orFalse()) {
        ContentScreen(
            user = userDataFlow,
            onClickLogout = { showLogoutDialog = true },
            onClickAlterTheme = { showAlterThemeDialog = true },
            onClickSyncLists = viewModel::syncLists,
            onBackPressed = viewModel::navigateBack
        )
    } else {
        ProfileNotAuthContent(
            onClickLogin = redirectToAuth,
            onClickAlterTheme = { showAlterThemeDialog = true },
            onBackPressed = viewModel::navigateBack
        )
    }

    if (showLogoutDialog) {
        ShowLogoutDialog(
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

@Composable
private fun ContentScreen(
    user: AuthenticatedUserDTO,
    onClickAlterTheme: () -> Unit = {},
    onClickLogout: () -> Unit = {},
    onClickSyncLists: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.profile_screen_title),
            onBackPressed = onBackPressed,
            extraIcon = ToolkitIconCatalog.Logout,
            onClickExtraIcon = { onClickLogout() },
        )
        Column(
            modifier = Modifier
                .padding(ToolkitSpacingMd)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolkitProfileContent(
                userName = user.name,
                userEmail = user.email,
                userImageUrl = user.photoUrl,
                isAuthenticated = user.isAuthenticated().orFalse(),
            )
            ToolkitSpaceHeight(ToolkitSpacingXl)
            ToolkitOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickSyncLists,
                text = stringResource(R.string.profile_screen_btn_sync),
                leadingIcon = ToolkitIconCatalog.CloudSync
            )
            ToolkitSpaceHeight(ToolkitSpacingXl)
            ToolkitOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickAlterTheme,
                text = stringResource(R.string.profile_screen_btn_alter_theme),
                leadingIcon = ToolkitIconCatalog.Palette
            )
        }
        ToolkitAppVersionContent(
            versionName = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE.toString()
        )
    }
}

@Composable
private fun ProfileNotAuthContent(
    onClickAlterTheme: () -> Unit = {},
    onClickLogin: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.profile_screen_title),
            onBackPressed = onBackPressed,
        )
        Column(
            modifier = Modifier
                .padding(ToolkitSpacingMd)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolkitProfileContent(onClickLogin = onClickLogin)
            ToolkitSpaceHeight(ToolkitSpacingXl)
            ToolkitOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickAlterTheme,
                text = stringResource(R.string.profile_screen_btn_alter_theme),
                leadingIcon = ToolkitIconCatalog.Palette
            )
        }
        ToolkitAppVersionContent(
            versionName = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE.toString()
        )
    }
}

@AppPreview
@Composable
private fun PreviewContentScreen() {
    ToolkitPreviewContainer {
        ContentScreen(
            AuthenticatedUserDTO(
                id = 0,
                name = "Fulano de Tal",
                email = "fulano@gmail.com",
                photoUrl = "https://avatars.githubusercontent.com/u/5596101?v=4"

            )
        )
    }
}

@AppPreview
@Composable
private fun PreviewProfileNotAuth() {
    ToolkitPreviewContainer {
        ProfileNotAuthContent()
    }
}