package com.vald3nir.shoppinglist.domain

import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO

data class CreateListDTO(
    val listId: Long? = null,
    val title: String = "",
    val items: List<ItemShoppingListDTO> = emptyList(),
    val query: String = ""
) {
    fun getItemsFiltered(): List<ItemShoppingListDTO> = if (query.isNotEmpty()) items.filter { it.filter(query) } else items
}