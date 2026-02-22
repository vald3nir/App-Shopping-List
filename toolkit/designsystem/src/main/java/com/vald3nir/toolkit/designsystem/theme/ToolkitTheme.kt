package com.vald3nir.toolkit.designsystem.theme

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.theme.brands.BaseThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.BlueThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.GreenThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.PurpleThemaBrand
import com.vald3nir.toolkit.designsystem.theme.brands.RedThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.YellowThemeBrand
import com.vald3nir.toolkit.designsystem.theme.color.DarkAndroidColorScheme
import com.vald3nir.toolkit.designsystem.theme.color.DarkDefaultColorScheme
import com.vald3nir.toolkit.designsystem.theme.color.LightAndroidColorScheme
import com.vald3nir.toolkit.designsystem.theme.color.LightDefaultColorScheme
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2.BLUE
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2.GREEN
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2.PURPLE
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2.RED
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2.YELLOW
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.DarkBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.GradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LightBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.LocalBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LocalTintTheme
import com.vald3nir.toolkit.designsystem.theme.providers.TintTheme
import com.vald3nir.toolkit.designsystem.theme.text.ToolkitTypography

@Composable
fun ToolkitTheme2(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeBrandEnum: ThemeBrandEnum2,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    val themeBrand = themeBrandEnum.toThemeBrand()
    val colorScheme = buildColorScheme(themeBrand, darkTheme, disableDynamicTheming)
    val gradientColors = colorScheme.buildGradientColor()
    val backgroundTheme = themeBrand.getBackgroundTheme(darkTheme)
    val tintTheme = colorScheme.buildTintTheme(disableDynamicTheming)

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

private fun ThemeBrandEnum2.toThemeBrand(): BaseThemeBrand = when (this) {
    BLUE -> BlueThemeBrand()
    GREEN -> GreenThemeBrand()
    PURPLE -> PurpleThemaBrand()
    RED -> RedThemeBrand()
    YELLOW -> YellowThemeBrand()
    else -> YellowThemeBrand() // todo valdenir criar um dark
}

@Composable
fun ToolkitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = buildColorScheme(androidTheme, darkTheme, disableDynamicTheming)
    val gradientColors = colorScheme.buildGradientColor()
    val backgroundTheme = colorScheme.buildBackgroundTheme(androidTheme, darkTheme)
    val tintTheme = colorScheme.buildTintTheme(disableDynamicTheming)

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

@SuppressLint("VisibleForTests")
@Composable
private fun buildColorScheme(
    themeBrand: BaseThemeBrand,
    darkTheme: Boolean,
    disableDynamicTheming: Boolean
): ColorScheme = if (!disableDynamicTheming && supportsDynamicTheming()) {
    LocalContext.current.loadDynamicColors(darkTheme)
} else {
    themeBrand.getColorScheme(darkTheme)
}

@RequiresApi(Build.VERSION_CODES.S)
private fun Context.loadDynamicColors(darkTheme: Boolean) = if (darkTheme) {
    dynamicDarkColorScheme(this)
} else {
    dynamicLightColorScheme(this)
}

@Composable
private fun ColorScheme.buildBackgroundTheme(androidTheme: Boolean, darkTheme: Boolean): BackgroundTheme {
    val defaultBackgroundTheme = BackgroundTheme(color = surface, tonalElevation = 2.dp)
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkBackgroundTheme else LightBackgroundTheme
        else -> defaultBackgroundTheme
    }
    return backgroundTheme
}


@Composable
private fun ColorScheme.buildGradientColor() = GradientColors(
    top = inverseOnSurface,
    bottom = primaryContainer,
    container = surface,
)

@Composable
private fun ColorScheme.buildTintTheme(disableDynamicTheming: Boolean) = if (!disableDynamicTheming && supportsDynamicTheming()) {
    TintTheme(primary)
} else {
    TintTheme()
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S