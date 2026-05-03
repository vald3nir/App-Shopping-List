package com.vald3nir.shoppinglist.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
internal data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
)