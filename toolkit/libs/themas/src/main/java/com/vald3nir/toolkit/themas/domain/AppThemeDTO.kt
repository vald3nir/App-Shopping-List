package com.vald3nir.toolkit.themas.domain

import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

data class AppThemeDTO(
    val themeBrand: ThemeBrandEnum2? = null,
    val themeConfigEnum: UIThemeConfigEnum? = null,
    val useDynamicColor: Boolean = false,
)