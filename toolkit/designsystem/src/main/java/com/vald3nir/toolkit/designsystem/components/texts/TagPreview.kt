package com.vald3nir.toolkit.designsystem.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitTag(followed = true, onClick = {}) {
            Text("Topic".uppercase())
        }
    }
}