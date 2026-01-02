package com.vald3nir.toolkit.designsystem.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.theme.color.DarkAndroidBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.color.DarkAndroidColorScheme
import com.vald3nir.toolkit.designsystem.theme.color.DarkAndroidGradientColors
import com.vald3nir.toolkit.designsystem.theme.color.DarkDefaultColorScheme
import com.vald3nir.toolkit.designsystem.theme.color.LightAndroidBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.color.LightAndroidColorScheme
import com.vald3nir.toolkit.designsystem.theme.color.LightAndroidGradientColors
import com.vald3nir.toolkit.designsystem.theme.color.LightDefaultColorScheme
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.GradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LocalBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LocalTintTheme
import com.vald3nir.toolkit.designsystem.theme.providers.TintTheme
import com.vald3nir.toolkit.designsystem.theme.text.ToolkitTypography

@Composable
fun ToolkitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = buildColorScheme(androidTheme, darkTheme, disableDynamicTheming)
    val gradientColors = colorScheme.buildGradientColor(androidTheme, darkTheme, disableDynamicTheming)
    val backgroundTheme = colorScheme.buildBackgroundTheme(androidTheme, darkTheme)
    val tintTheme = colorScheme.buildTintTheme(androidTheme, disableDynamicTheming)

    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ToolkitTypography,
            content = content,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S


@SuppressLint("VisibleForTests")
@Composable
private fun buildColorScheme(androidTheme: Boolean, darkTheme: Boolean, disableDynamicTheming: Boolean): ColorScheme = when {
    androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
    !disableDynamicTheming && supportsDynamicTheming() -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
}

@Composable
private fun ColorScheme.buildGradientColor(androidTheme: Boolean, darkTheme: Boolean, disableDynamicTheming: Boolean): GradientColors {
    val emptyGradientColors = GradientColors(container = surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = inverseOnSurface,
        bottom = primaryContainer,
        container = surface,
    )
    return when {
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
}

@Composable
private fun ColorScheme.buildBackgroundTheme(androidTheme: Boolean, darkTheme: Boolean): BackgroundTheme {
    val defaultBackgroundTheme = BackgroundTheme(color = surface, tonalElevation = 2.dp)
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    return backgroundTheme
}

@Composable
private fun ColorScheme.buildTintTheme(androidTheme: Boolean, disableDynamicTheming: Boolean): TintTheme {
    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(primary)
        else -> TintTheme()
    }
    return tintTheme
}