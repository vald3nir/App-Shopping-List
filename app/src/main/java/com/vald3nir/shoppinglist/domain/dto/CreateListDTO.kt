package com.vald3nir.shoppinglist.domain.dto

data class CreateListDTO(
    val listId: String? = null,
    val title: String = "",
    val items: List<ItemShoppingListDTO> = emptyList(),
    val query: String = ""
) {
    fun getItemsFiltered(): List<ItemShoppingListDTO> = if (query.isNotEmpty()) items.filter { it.filter(query) } else items
}