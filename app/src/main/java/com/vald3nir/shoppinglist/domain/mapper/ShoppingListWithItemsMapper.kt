package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItemsModel
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.toolkit.helpers.utils.parses.fromJsonToObject

fun List<String?>.toShoppingListWithItemsModel(): List<ShoppingListWithItemsModel> {
    val list = mutableListOf<ShoppingListWithItemsModel>()
    this.forEach { dataJson ->
        if (!dataJson.isNullOrEmpty()) {
            list.add(fromJsonToObject<ShoppingListWithItemsModel>(dataJson))
        }
    }
    return list
}

fun ShoppingListWithItemsModel.toShoppingListDTO() = ShoppingListDTO(
    id = this.shoppingList.id,
    title = this.shoppingList.title,
    date = this.shoppingList.date,
    items = this.items.toDTOList()
)