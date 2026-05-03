package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitFixedButton(
    modifier: Modifier = Modifier,
    label: String,
    showLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ToolkitBaseButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingLg),
        onClick = onClick,
        enabled = enabled,
        content = {
            if (showLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                ToolkitText(text = label, style = ToolkitTextStyle.LabelMedium)
            }
        }
    )
}