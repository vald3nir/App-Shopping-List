package com.vald3nir.shoppinglist.core.domain.dto

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ItemShoppingListDTO(
    val uuid: String = UUID.randomUUID().toString(),
    val id: Long? = null,
    val shoppingListId: Long? = null,
    val category: String? = null,
    val product: String? = null,
    val iconURL: String? = null,
    val quantity: Int = 1,
    val unitPrice: Double = 0.0,
    var isAdd: Boolean = false
) {
    val totalValue: Double
        get() = unitPrice * quantity

    fun isValid() = !product.isNullOrEmpty() && quantity > 0

    fun filter(query: String) = category?.contains(query, ignoreCase = true) == true || product?.contains(query, ignoreCase = true) == true

    fun description() = "$product - $quantity Uni."
}