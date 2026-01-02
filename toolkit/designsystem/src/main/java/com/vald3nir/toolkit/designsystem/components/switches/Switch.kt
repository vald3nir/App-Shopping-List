package com.vald3nir.toolkit.designsystem.components.switches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitSwitch(startEnable: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(checked = startEnable, onCheckedChange = { onCheckedChange(it) })
}

@Composable
fun ToolkitSwitchLabeled(
    labelStart: String,
    labelEnd: String,
    startEnable: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = labelStart,
            style = ToolkitTextStyle.TitleMedium
        )
        Switch(checked = startEnable, onCheckedChange = { onCheckedChange(it) })
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = labelEnd,
            style = ToolkitTextStyle.TitleMedium
        )
    }
}