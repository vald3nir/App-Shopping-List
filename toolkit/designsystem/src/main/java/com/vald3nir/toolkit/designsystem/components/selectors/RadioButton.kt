package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun ToolkitRadioButton(
    label: String,
    modifier: Modifier = Modifier,
    checked: Boolean,
    onClick: () -> Unit = {},
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick() }) {
        RadioButton(
            modifier = modifier,
            selected = checked,
            onClick = onClick,
        )
        ToolkitText(text = label, style = ToolkitTextStyle.LabelMedium)
        DefaultSpaceWidth()
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier.size(100.dp, 100.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitRadioButton(label = "label", checked = false)
                ToolkitRadioButton(label = "label", checked = true)
            }
        }
    }
}