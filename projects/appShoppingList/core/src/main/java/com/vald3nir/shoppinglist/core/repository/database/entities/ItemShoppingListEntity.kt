package com.vald3nir.shoppinglist.core.repository.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.core.utils.extensions.getCurrentDate

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
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val uuid: String? = null, // for sync
    val shoppingListId: Long? = null,
    val category: String? = null,
    val product: String? = null,
    val iconURL: String? = null,
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    val isAdd: Boolean = false, // Flag to indicate if the item is added to the shopping list
    val createdAt: String = getCurrentDate(),
    val lastUpdated: Long = System.currentTimeMillis(),
)