package com.vald3nir.toolkit.designsystem.theme.color

import androidx.annotation.VisibleForTesting
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.Blue10
import com.vald3nir.toolkit.designsystem.theme.providers.Blue40
import com.vald3nir.toolkit.designsystem.theme.providers.Blue90
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen10
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen40
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreen90
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreenGray10
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreenGray20
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreenGray95
import com.vald3nir.toolkit.designsystem.theme.providers.DarkGreenGray99
import com.vald3nir.toolkit.designsystem.theme.providers.DarkPurpleGray10
import com.vald3nir.toolkit.designsystem.theme.providers.DarkPurpleGray20
import com.vald3nir.toolkit.designsystem.theme.providers.DarkPurpleGray95
import com.vald3nir.toolkit.designsystem.theme.providers.DarkPurpleGray99
import com.vald3nir.toolkit.designsystem.theme.providers.GradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.Green10
import com.vald3nir.toolkit.designsystem.theme.providers.Green40
import com.vald3nir.toolkit.designsystem.theme.providers.Green90
import com.vald3nir.toolkit.designsystem.theme.providers.GreenGray30
import com.vald3nir.toolkit.designsystem.theme.providers.GreenGray50
import com.vald3nir.toolkit.designsystem.theme.providers.GreenGray90
import com.vald3nir.toolkit.designsystem.theme.providers.Orange10
import com.vald3nir.toolkit.designsystem.theme.providers.Orange40
import com.vald3nir.toolkit.designsystem.theme.providers.Orange90
import com.vald3nir.toolkit.designsystem.theme.providers.Purple10
import com.vald3nir.toolkit.designsystem.theme.providers.Purple40
import com.vald3nir.toolkit.designsystem.theme.providers.Purple90
import com.vald3nir.toolkit.designsystem.theme.providers.PurpleGray30
import com.vald3nir.toolkit.designsystem.theme.providers.PurpleGray50
import com.vald3nir.toolkit.designsystem.theme.providers.PurpleGray90
import com.vald3nir.toolkit.designsystem.theme.providers.Red10
import com.vald3nir.toolkit.designsystem.theme.providers.Red40
import com.vald3nir.toolkit.designsystem.theme.providers.Red90
import com.vald3nir.toolkit.designsystem.theme.providers.Teal10
import com.vald3nir.toolkit.designsystem.theme.providers.Teal40
import com.vald3nir.toolkit.designsystem.theme.providers.Teal90




@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Purple90,
    onPrimaryContainer = Purple10,
    secondary = Orange40,
    onSecondary = Color.White,
    secondaryContainer = Orange90,
    onSecondaryContainer = Orange10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkPurpleGray99,
    onBackground = DarkPurpleGray10,
    surface = DarkPurpleGray99,
    onSurface = DarkPurpleGray10,
    surfaceVariant = PurpleGray90,
    onSurfaceVariant = PurpleGray30,
    inverseSurface = DarkPurpleGray20,
    inverseOnSurface = DarkPurpleGray95,
    outline = PurpleGray50,
)

@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    secondaryContainer = DarkGreen90,
    onSecondaryContainer = DarkGreen10,
    tertiary = Teal40,
    onTertiary = Color.White,
    tertiaryContainer = Teal90,
    onTertiaryContainer = Teal10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkGreenGray99,
    onBackground = DarkGreenGray10,
    surface = DarkGreenGray99,
    onSurface = DarkGreenGray10,
    surfaceVariant = GreenGray90,
    onSurfaceVariant = GreenGray30,
    inverseSurface = DarkGreenGray20,
    inverseOnSurface = DarkGreenGray95,
    outline = GreenGray50,
)