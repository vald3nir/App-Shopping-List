package com.vald3nir.shoppinglist.domain

import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

internal data class ProfileScreenDTO(
    val user: AuthenticatedUserDTO? = null,
    val brand: ThemeBrandEnum2? = null,
    val useDynamicColor: Boolean? = null,
    val uIThemeConfigEnum: UIThemeConfigEnum? = null,
)