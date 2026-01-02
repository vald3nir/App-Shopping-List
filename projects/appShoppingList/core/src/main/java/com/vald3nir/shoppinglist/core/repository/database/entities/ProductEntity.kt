package com.vald3nir.shoppinglist.core.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.core.utils.extensions.getCurrentDate

@Entity(tableName = "products")
internal data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val categoryId: String? = null,
    val brandId: String? = null,
    val createdAt: String = getCurrentDate(),
    val lastUpdated: Long = System.currentTimeMillis(),
)