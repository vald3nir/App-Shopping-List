package com.vald3nir.shoppinglist.presentation.app

import com.vald3nir.shoppinglist.core.domain.dto.AppThemeDTO
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

internal sealed interface MainUiState {

    data object Loading : MainUiState

    data class Success(val userData: AppThemeDTO) : MainUiState {

        override val shouldDisableDynamicTheming = !userData.useDynamicColor

        override val shouldUseAndroidTheme: Boolean = when (userData.themeBrand) {
            ThemeBrandEnum.DEFAULT -> false
            ThemeBrandEnum.ANDROID -> true
        }

        override fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) =
            when (userData.themeConfigEnum) {
                UIThemeConfigEnum.FOLLOW_SYSTEM -> isSystemDarkTheme
                UIThemeConfigEnum.LIGHT -> false
                UIThemeConfigEnum.DARK -> true
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
    val shouldUseAndroidTheme: Boolean get() = false

    /**
     * Returns `true` if dark theme should be used.
     */
    fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) = isSystemDarkTheme
}
