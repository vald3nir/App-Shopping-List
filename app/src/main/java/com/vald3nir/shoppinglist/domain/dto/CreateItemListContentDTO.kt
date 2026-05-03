package com.vald3nir.shoppinglist.domain.dto

internal data class CreateItemListContentDTO(
    val topItems: List<ItemShoppingListDTO> = emptyList(),
    val productNames: List<String> = emptyList()
) {

    val topProduct get() : List<String> = topItems.mapNotNull { it.product }.distinct()

    fun selectItemByTopProduct(topProduct: String): ItemShoppingListDTO = topItems.first { it.product == topProduct }
}