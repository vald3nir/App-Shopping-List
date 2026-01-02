package com.vald3nir.shoppinglist.core.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.core.utils.extensions.getCurrentDate

@Entity(tableName = "shopping_list")
internal data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val uuid: String? = null, // for sync
    val title: String? = null,
    val createdAt: String = getCurrentDate(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val inEditing: Boolean = false
)