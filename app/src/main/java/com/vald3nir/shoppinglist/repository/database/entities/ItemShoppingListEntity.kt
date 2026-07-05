package com.vald3nir.shoppinglist.repository.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.core.utils.security.generateUUID

@Entity(
    tableName = "item_shopping_list",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [Index(value = ["shoppingListId"])]
)
internal data class ItemShoppingListEntity(
    @PrimaryKey
    val id: String = generateUUID(),
    val shoppingListId: String?, // UUID
    val product: String? = null,
    val quantity: Int = 1,
    val unitPrice: Double = 0.0,
    val onCart: Boolean = false, // Flag to indicate if the item is added to the shopping list
)