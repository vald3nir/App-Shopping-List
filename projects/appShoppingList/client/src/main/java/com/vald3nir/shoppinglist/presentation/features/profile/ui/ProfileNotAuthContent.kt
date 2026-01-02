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
import com.vald3nir.shoppinglist.domain.ProfileScreenDTO
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitOutlinedButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
internal fun ProfileNotAuthContent(
    profileData: ProfileScreenDTO,
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
            ProfileHeader(user = profileData.user, onClickLogin = onClickLogin)
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
    ToolkitTheme {
        ToolkitBackground {
            ProfileNotAuthContent(ProfileScreenDTO())
        }
    }
}