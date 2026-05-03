package com.vald3nir.shoppinglist.domain.dto

import com.vald3nir.shoppinglist.domain.enums.ItemsFilterEnum

data class ListDetailDTO(
    val listId: String? = null,
    val title: String = "",
    val items: List<ItemShoppingListDTO> = emptyList(),
    val itemsOffCart: List<ItemShoppingListDTO> = emptyList(),
    val filter: ItemsFilterEnum = ItemsFilterEnum.ON_CART,
    val query: String = ""
) {
    fun getItemsFiltered(): List<ItemShoppingListDTO> {
        if (query.isNotEmpty()) return items.filter { it.filter(query) }
        return if (filter == ItemsFilterEnum.OFF_CART) {
            items.filter { !it.onCart }
        } else {
            items.filter { it.onCart }
        }
    }

    fun calculateShoppingCart(): Triple<Int, Int, Double> {
        var countAdded = 0
        var countNotAdded = 0
        var totalPrice = 0.0
        items.forEach { item ->
            if (item.onCart) {
                countAdded++
                totalPrice += item.totalValue
            } else {
                countNotAdded++
            }
        }
        return Triple(countAdded, countNotAdded, totalPrice)
    }
}