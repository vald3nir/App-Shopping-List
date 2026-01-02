package com.vald3nir.shoppinglist.domain

import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.enums.ItemsFilterEnum

data class ListDetailDTO(
    val listId: Long? = null,
    val title: String = "",
    val items: List<ItemShoppingListDTO> = emptyList(),
    val itemsOffCart: List<ItemShoppingListDTO> = emptyList(),
    val filter: ItemsFilterEnum = ItemsFilterEnum.ON_CART,
    val query: String = ""
) {
    fun getItemsFiltered(): List<ItemShoppingListDTO> {
        if (query.isNotEmpty()) return items.filter { it.filter(query) }
        return if (filter == ItemsFilterEnum.OFF_CART) {
            items.filter { !it.isAdd }
        } else {
            items.filter { it.isAdd }
        }
    }

    fun calculateShoppingCart(): Triple<Int, Int, Double> {
        var countAdded = 0
        var countNotAdded = 0
        var totalPrice = 0.0
        items.forEach { item ->
            if (item.isAdd) {
                countAdded++
                totalPrice += item.totalValue
            } else {
                countNotAdded++
            }
        }
        return Triple(countAdded, countNotAdded, totalPrice)
    }
}