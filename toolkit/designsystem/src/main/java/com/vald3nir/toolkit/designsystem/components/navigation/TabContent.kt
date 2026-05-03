package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun ToolkitTabContent(isVisible: Boolean, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier.graphicsLayer {
            alpha = if (isVisible) 1f else 0f
        }
    ) {
        content()
    }
}