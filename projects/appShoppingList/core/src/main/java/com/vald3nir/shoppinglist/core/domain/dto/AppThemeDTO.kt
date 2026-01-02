package com.vald3nir.shoppinglist.core.domain.dto

import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

data class AppThemeDTO(
    val themeBrand: ThemeBrandEnum = ThemeBrandEnum.DEFAULT,
    val themeConfigEnum: UIThemeConfigEnum = UIThemeConfigEnum.FOLLOW_SYSTEM,
    val useDynamicColor: Boolean = false,
)