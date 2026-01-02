package com.vald3nir.shoppinglist.domain

import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.dto.AppThemeDTO
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO

internal data class HomeScreenDTO(
    val user: AuthenticatedUserDTO? = null,
    val lists: List<ShoppingListDTO>? = null,
    val appThema: AppThemeDTO? = null
) {
    fun hasUserLogged() = user?.id != null
}