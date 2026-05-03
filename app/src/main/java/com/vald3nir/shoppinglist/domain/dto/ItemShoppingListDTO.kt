package com.vald3nir.shoppinglist.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class ItemShoppingListDTO(
    val id: String? = null,
    val shoppingListId: String? = null,
    val product: String? = null,
    val iconURL: String? = null,
    val quantity: Int = 1,
    val unitPrice: Double = 0.0,
    var onCart: Boolean = false
) {
    val totalValue: Double get() = unitPrice * quantity

    fun isValid() = !product.isNullOrEmpty() && quantity > 0

    fun filter(query: String) = product?.contains(query, ignoreCase = true) == true

}