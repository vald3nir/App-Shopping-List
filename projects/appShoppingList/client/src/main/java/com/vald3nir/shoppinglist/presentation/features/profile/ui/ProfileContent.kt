package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.components.AppTopBar
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.shoppinglist.domain.ProfileScreenDTO
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitAlertDialog
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

@Composable
internal fun ProfileContent(
    profileData: ProfileScreenDTO,
    onChangeThemeBrand: (themeBrand: ThemeBrandEnum) -> Unit = {},
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit = {},
    onChangeDarkThemeConfig: (uIThemeConfigEnum: UIThemeConfigEnum) -> Unit = {},
    onClickLogout: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.profile_screen_title),
            onBackPressed = onBackPressed,
            extraIcon = ToolkitIconCatalog.Logout,
            onClickExtraIcon = { showLogoutDialog = true },
        )
        Column(
            modifier = Modifier
                .padding(ToolkitSpacingMd)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader(user = profileData.user)
            ToolkitSpaceHeight(ToolkitSpacingXl)
            ProfileThema(
                settingsUiState = profileData,
                onChangeThemeBrand = onChangeThemeBrand,
                onChangeDynamicColorPreference = onChangeDynamicColorPreference,
                onChangeDarkThemeConfig = onChangeDarkThemeConfig
            )
        }
        ProfileFooter()
    }
    if (showLogoutDialog) {
        ToolkitAlertDialog(
            title = stringResource(R.string.profile_dialog_logout_title),
            description = stringResource(R.string.profile_dialog_logout_description),
            btnConfirmLabel = stringResource(R.string.logout),
            btnCancelLabel = stringResource(R.string.cancel),
            onConfirm = {
                onClickLogout()
                showLogoutDialog = false
            },
            onCancel = { showLogoutDialog = false }
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground {
            ProfileContent(ProfileScreenDTO())
        }
    }
}