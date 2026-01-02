package com.vald3nir.toolkit.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.BigSpaceHeight
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
internal fun BoxScope.Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultSpaceHeight()
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.AccountCircle,
            modifier = Modifier.size(48.dp)
        )
        DefaultSpaceHeight()
        ToolkitText(
            text = stringResource(R.string.auth_screen_title),
            style = ToolkitTextStyle.TitleMedium
        )
        BigSpaceHeight()
        LoginInfo()
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
        ToolkitBackground {
            Box {
                Header()
            }
        }
    }
}