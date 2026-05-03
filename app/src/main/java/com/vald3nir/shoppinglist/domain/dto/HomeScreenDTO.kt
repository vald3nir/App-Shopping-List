package com.vald3nir.shoppinglist.domain.dto

import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.core.theme.domain.AppThemeDTO

internal data class HomeScreenDTO(
    val user: AuthenticatedUserDTO? = null,
    val lists: List<ShoppingListDTO>? = null,
    val appThema: AppThemeDTO? = null
)