package com.vald3nir.shoppinglist.core.ui.thema

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    ToolkitTheme(
        androidTheme = androidTheme,
        darkTheme = darkTheme,
        disableDynamicTheming = disableDynamicTheming,
        content = content
    )
}