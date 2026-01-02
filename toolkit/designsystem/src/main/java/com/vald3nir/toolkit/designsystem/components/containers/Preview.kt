package com.vald3nir.toolkit.designsystem.components.containers

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun BackgroundDefault() {
    ToolkitTheme(disableDynamicTheming = true) {
        ToolkitBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
private fun BackgroundDynamic() {
    ToolkitTheme(disableDynamicTheming = false) {
        ToolkitBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
private fun BackgroundAndroid() {
    ToolkitTheme(androidTheme = true) {
        ToolkitBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
private fun GradientBackgroundDefault() {
    ToolkitTheme(disableDynamicTheming = true) {
        ToolkitGradientBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
private fun GradientBackgroundDynamic() {
    ToolkitTheme(disableDynamicTheming = false) {
        ToolkitGradientBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
private fun GradientBackgroundAndroid() {
    ToolkitTheme(androidTheme = true) {
        ToolkitGradientBackground(Modifier.size(100.dp), content = {})
    }
}