package com.vald3nir.toolkit.designsystem.components.containers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.vald3nir.toolkit.designsystem.components.defaultSpace

@Composable
fun ToolkitSurface(paddingValue: Dp = defaultSpace, content: @Composable (PaddingValues) -> Unit) {
    Surface {
        val contentPadding = WindowInsets
            .systemBars
            .add(WindowInsets(left = paddingValue, top = paddingValue, right = paddingValue, bottom = paddingValue))
            .asPaddingValues()
        content(contentPadding)
    }
}