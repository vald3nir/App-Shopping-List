package com.vald3nir.toolkit.designsystem.components.radiobuttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

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