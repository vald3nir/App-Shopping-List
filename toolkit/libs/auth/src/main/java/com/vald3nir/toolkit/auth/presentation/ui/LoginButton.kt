package com.vald3nir.toolkit.auth.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.loadings.ToolkitLoadingWheel
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
internal fun LoginButton(
    enabled: Boolean = true,
    showLoading: Boolean = false,
    onClickLogin: () -> Unit = {}
) {
    Button(
        enabled = enabled,
        onClick = {
            if (!showLoading) onClickLogin()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_google),
                tint = Color.Unspecified,
                contentDescription = stringResource(R.string.auth_btn_login_label),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (showLoading) {
                ToolkitLoadingWheel()
            } else {
                ToolkitText(
                    text = stringResource(R.string.auth_sign_in_with_your_google_account),
                    style = ToolkitTextStyle.LabelMedium,
                    textColor = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(
            modifier = Modifier.size(width = 350.dp, height = 150.dp),
            content = {
                Column {
                    LoginButton(enabled = true)
                    LoginButton(enabled = false)
                }
            }
        )
    }
}