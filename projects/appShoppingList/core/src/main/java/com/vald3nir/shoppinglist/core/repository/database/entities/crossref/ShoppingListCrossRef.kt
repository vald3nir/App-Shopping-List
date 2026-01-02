package com.vald3nir.shoppinglist.core.repository.database.entities.crossref

import androidx.room.Embedded
import androidx.room.Relation
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ShoppingListEntity

internal data class ShoppingListCrossRef(
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingListId"
    )
    val items: List<ItemShoppingListEntity>
)