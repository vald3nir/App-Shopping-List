package com.vald3nir.shoppinglist.repository.database.entities

import androidx.room.Embedded
import androidx.room.Relation

internal data class ShoppingListCrossRef(
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingListId"
    )
    val items: List<ItemShoppingListEntity>
)