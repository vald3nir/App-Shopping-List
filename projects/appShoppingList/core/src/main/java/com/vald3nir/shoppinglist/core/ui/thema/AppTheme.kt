package com.vald3nir.shoppinglist.core.ui.thema

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme2
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2

@Composable
fun AppTheme(
    themeBrandEnum: ThemeBrandEnum2 = ThemeBrandEnum2.BLUE,
    darkTheme: Boolean = isSystemInDarkTheme(),
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    ToolkitTheme2(
        themeBrandEnum = themeBrandEnum,
        darkTheme = darkTheme,
        disableDynamicTheming = disableDynamicTheming,
        content = content
    )
}