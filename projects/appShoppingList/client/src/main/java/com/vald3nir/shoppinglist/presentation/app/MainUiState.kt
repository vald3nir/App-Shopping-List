package com.vald3nir.shoppinglist.presentation.app

import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import com.vald3nir.toolkit.themas.domain.AppThemeDTO

internal sealed interface MainUiState {

    data object Loading : MainUiState

    data class Success(val appTheme: AppThemeDTO) : MainUiState {

        override val shouldDisableDynamicTheming = !appTheme.useDynamicColor

        override val themaEnum: ThemeBrandEnum2 = appTheme.themeBrand ?: ThemeBrandEnum2.BLUE

        override fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) =
            when (appTheme.themeConfigEnum) {
                UIThemeConfigEnum.FOLLOW_SYSTEM -> isSystemDarkTheme
                UIThemeConfigEnum.LIGHT -> false
                UIThemeConfigEnum.DARK -> true
                else -> true
            }
    }

    /**
     * Returns `true` if the state wasn't loaded yet and it should keep showing the splash screen.
     */
    fun shouldKeepSplashScreen() = this is Loading

    /**
     * Returns `true` if the dynamic color is disabled.
     */
    val shouldDisableDynamicTheming: Boolean get() = true

    /**
     * Returns `true` if the Android theme should be used.
     */
//    val shouldUseAndroidTheme: Boolean get() = false
    val themaEnum: ThemeBrandEnum2 get() = ThemeBrandEnum2.BLUE

    /**
     * Returns `true` if dark theme should be used.
     */
    fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) = isSystemDarkTheme
}
