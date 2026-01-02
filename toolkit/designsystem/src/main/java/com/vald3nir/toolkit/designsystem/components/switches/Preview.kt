package com.vald3nir.toolkit.designsystem.components.switches

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitSwitch(startEnable = true, onCheckedChange = {})
                ToolkitSwitch(startEnable = false, onCheckedChange = {})
                ToolkitSwitchLabeled(
                    labelStart = "Sim",
                    labelEnd = "Não",
                    startEnable = true,
                    onCheckedChange = {}
                )
            }
        }
    }
}