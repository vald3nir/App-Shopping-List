package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun ProfileFooter() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        HorizontalDivider()
        ToolkitSpaceHeight()
        ToolkitText(text = stringResource(R.string.profile_screen_app_version), style = ToolkitTextStyle.BodySmall)
        ToolkitSpaceHeight(ToolkitSpacingXs)
        ToolkitText(text = "${BuildConfig.VERSION_NAME} (Build ${BuildConfig.VERSION_CODE})", style = ToolkitTextStyle.LabelSmall)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    AppTheme {
        ToolkitBackground {
            ProfileFooter()
        }
    }
}