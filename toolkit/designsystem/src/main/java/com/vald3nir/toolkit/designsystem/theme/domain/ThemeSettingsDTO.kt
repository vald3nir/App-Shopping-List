package com.vald3nir.toolkit.designsystem.theme.domain

data class ThemeSettingsDTO(
    val themaBrandEnum: ThemeBrandEnum2,
    val darkTheme: Boolean,
    val disableDynamicTheming: Boolean,
)