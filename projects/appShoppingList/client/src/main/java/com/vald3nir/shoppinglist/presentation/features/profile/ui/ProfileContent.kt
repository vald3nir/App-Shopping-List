package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitOutlinedButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun ProfileContent(
    profileData: ProfileScreenDTO,
    onClickAlterTheme: () -> Unit = {},
    onClickLogout: () -> Unit = {},
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
            ProfileHeader(user = profileData.user)
            ToolkitSpaceHeight(ToolkitSpacingXl)
            ToolkitOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickAlterTheme,
                text = stringResource(R.string.profile_screen_btn_alter_theme),
                leadingIcon = ToolkitIconCatalog.Palette
            )
        }
        ProfileFooter()
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