package com.vald3nir.toolkit.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.core.utils.extensions.openWifiSettings
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitTextButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
internal fun Body(
    modifier: Modifier = Modifier,
    showLoading: Boolean = false,
    hasInternetConnection: Boolean = true,
    onClickLogin: () -> Unit = {}
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButton(
            enabled = hasInternetConnection,
            showLoading = showLoading,
            onClickLogin = onClickLogin
        )
        DefaultSpaceHeight()
        val infoText = stringResource(
            if (hasInternetConnection) {
                (R.string.auth_create_account_description)
            } else {
                R.string.auth_create_account_offline_warning
            }
        )
        ToolkitText(
            text = infoText,
            style = ToolkitTextStyle.BodyMedium
        )
        if (!hasInternetConnection) {
            ToolkitTextButton(
                onClick = { context.openWifiSettings() },
                label = stringResource(R.string.auth_wifi_connect),
                leadingIcon = ToolkitIconCatalog.Wifi,
                trailingIcon = ToolkitIconCatalog.ChevronRight
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(
            modifier = Modifier.size(width = 350.dp, height = 500.dp),
            content = {
                Column {
                    Body(hasInternetConnection = true)
                    DefaultSpaceHeight()
                    Body(hasInternetConnection = false)
                }
            }
        )
    }
}