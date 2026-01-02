package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitSurface
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun ScreenLoading() {
    ToolkitSurface {
        ToolkitLoadingFullscreen()
    }
}