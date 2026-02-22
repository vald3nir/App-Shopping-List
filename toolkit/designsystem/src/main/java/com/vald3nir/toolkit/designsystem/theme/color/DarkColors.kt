package com.vald3nir.toolkit.designsystem.theme.color

import androidx.annotation.VisibleForTesting
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.Blue20
import com.vald3nir.toolkit.designsystem.theme.providers.Blue30
import com.vald3nir.toolkit.designsystem.theme.providers.Blue80
import com.vald3nir.toolkit.designsystem.theme.providers.Blue90
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen20
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen30
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen80
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen90
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreenGray10
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreenGray90
import com.vald3nir.toolkit.designsystem.theme.providers.DarkPurpleGray10
import com.vald3nir.toolkit.designsystem.theme.providers.DarkPurpleGray90
import com.vald3nir.toolkit.designsystem.theme.providers.GradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.Green20
import com.vald3nir.toolkit.designsystem.theme.providers.Green30
import com.vald3nir.toolkit.designsystem.theme.providers.Green80
import com.vald3nir.toolkit.designsystem.theme.providers.Green90
import com.vald3nir.toolkit.designsystem.theme.providers.GreenGray30
import com.vald3nir.toolkit.designsystem.theme.providers.GreenGray60
import com.vald3nir.toolkit.designsystem.theme.providers.GreenGray80
import com.vald3nir.toolkit.designsystem.theme.providers.Orange20
import com.vald3nir.toolkit.designsystem.theme.providers.Orange30
import com.vald3nir.toolkit.designsystem.theme.providers.Orange80
import com.vald3nir.toolkit.designsystem.theme.providers.Orange90
import com.vald3nir.toolkit.designsystem.theme.providers.Purple20
import com.vald3nir.toolkit.designsystem.theme.providers.Purple30
import com.vald3nir.toolkit.designsystem.theme.providers.Purple80
import com.vald3nir.toolkit.designsystem.theme.providers.Purple90
import com.vald3nir.toolkit.designsystem.theme.providers.PurpleGray30
import com.vald3nir.toolkit.designsystem.theme.providers.PurpleGray60
import com.vald3nir.toolkit.designsystem.theme.providers.PurpleGray80
import com.vald3nir.toolkit.designsystem.theme.providers.Red20
import com.vald3nir.toolkit.designsystem.theme.providers.Red30
import com.vald3nir.toolkit.designsystem.theme.providers.Red80
import com.vald3nir.toolkit.designsystem.theme.providers.Red90
import com.vald3nir.toolkit.designsystem.theme.providers.Teal20
import com.vald3nir.toolkit.designsystem.theme.providers.Teal30
import com.vald3nir.toolkit.designsystem.theme.providers.Teal80
import com.vald3nir.toolkit.designsystem.theme.providers.Teal90



@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple20,
    primaryContainer = Purple30,
    onPrimaryContainer = Purple90,
    secondary = Orange80,
    onSecondary = Orange20,
    secondaryContainer = Orange30,
    onSecondaryContainer = Orange90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkPurpleGray10,
    onBackground = DarkPurpleGray90,
    surface = DarkPurpleGray10,
    onSurface = DarkPurpleGray90,
    surfaceVariant = PurpleGray30,
    onSurfaceVariant = PurpleGray80,
    inverseSurface = DarkPurpleGray90,
    inverseOnSurface = DarkPurpleGray10,
    outline = PurpleGray60,
)

@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = Green80,
    onPrimary = Green20,
    primaryContainer = Green30,
    onPrimaryContainer = Green90,
    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    secondaryContainer = DarkGreen30,
    onSecondaryContainer = DarkGreen90,
    tertiary = Teal80,
    onTertiary = Teal20,
    tertiaryContainer = Teal30,
    onTertiaryContainer = Teal90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkGreenGray10,
    onBackground = DarkGreenGray90,
    surface = DarkGreenGray10,
    onSurface = DarkGreenGray90,
    surfaceVariant = GreenGray30,
    onSurfaceVariant = GreenGray80,
    inverseSurface = DarkGreenGray90,
    inverseOnSurface = DarkGreenGray10,
    outline = GreenGray60,
)