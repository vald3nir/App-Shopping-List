package com.vald3nir.shoppinglist.core.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListDTO(
    val id: Long? = null,
    val title: String? = null,
    val date: String? = null,
    var items: List<ItemShoppingListDTO> = emptyList()
) {
    fun filter(query: String) = title?.contains(query, ignoreCase = true) == true || date?.contains(query, ignoreCase = true) == true
}