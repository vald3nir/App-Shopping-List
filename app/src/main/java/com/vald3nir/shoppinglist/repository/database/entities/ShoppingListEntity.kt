package com.vald3nir.shoppinglist.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.core.utils.extensions.getISODate
import com.vald3nir.toolkit.core.utils.security.generateUUID

@Entity(tableName = "shopping_list")
internal data class ShoppingListEntity(
    @PrimaryKey
    val id: String = generateUUID(),
    val title: String? = null,
    val inEditing: Boolean = false,
    val createdAt: String = getISODate(),
    val markDeleted: Boolean = false, // Flag to indicate if the item has been deleted
)