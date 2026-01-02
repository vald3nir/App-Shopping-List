package com.vald3nir.toolkit.auth.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.core.utils.extensions.openWifiSettings
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.notifications.ToolkitDisclaimer
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
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd)
    ) {

        item { LoginInfo() }

        item {
            LoginButton(
                enabled = hasInternetConnection,
                showLoading = showLoading,
                onClickLogin = onClickLogin
            )
        }

        if (!hasInternetConnection) {
            item {
                ToolkitDisclaimer(
                    description = stringResource(R.string.auth_create_account_offline_warning),
                    imageVector = ToolkitIconCatalog.WifiOff,
                    linkText = stringResource(R.string.auth_wifi_connect),
                    onClickLink = { context.openWifiSettings() }
                )
            }
        }
    }
}

@Composable
private fun LoginInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.CloudSync,
            modifier = Modifier.size(32.dp)
        )
        DefaultSpaceWidth()
        ToolkitText(
            text = stringResource(R.string.auth_login_info),
            style = ToolkitTextStyle.LabelMedium,
        )
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(
            modifier = Modifier.size(width = 350.dp, height = 500.dp),
            content = {
                Body(hasInternetConnection = false)
            }
        )
    }
}