package com.vald3nir.shoppinglist.domain.dto

import com.vald3nir.toolkit.core.utils.extensions.toDateReduced
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListDTO(
    val id: String? = null,
    val title: String? = null,
    val createdAt: String? = null,
    val inEditing: Boolean = false,
    var items: List<ItemShoppingListDTO> = emptyList()
) {
    val price: Double get() = items.sumOf { if (it.onCart) it.totalValue else 0.0 }
    val dateLabel: String get() = createdAt.toDateReduced()

    fun filter(query: String) = title?.contains(query, ignoreCase = true) == true || createdAt?.contains(query, ignoreCase = true) == true
}