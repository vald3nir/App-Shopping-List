package com.vald3nir.shoppinglist.domain.dto

data class ScreenItemListDTO(
    val productNames: List<String> = emptyList(),
    val item: ItemShoppingListDTO = ItemShoppingListDTO(),
) {
    val currentProduct: String get() = item.product.orEmpty()

    val isValid: Boolean get() = item.isValid()
}