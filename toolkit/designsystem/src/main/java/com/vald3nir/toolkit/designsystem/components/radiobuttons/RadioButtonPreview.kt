package com.vald3nir.toolkit.designsystem.components.radiobuttons

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitSurface
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    ToolkitTheme {
        ToolkitSurface {
            Column {
                ToolkitRadioButton(label = "label", checked = false)
                ToolkitRadioButton(label = "label", checked = true)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    ToolkitTheme(darkTheme = true) {
        ToolkitSurface {
            Column {
                ToolkitRadioButton(label = "label", checked = false)
                ToolkitRadioButton(label = "label", checked = true)
            }
        }
    }
}