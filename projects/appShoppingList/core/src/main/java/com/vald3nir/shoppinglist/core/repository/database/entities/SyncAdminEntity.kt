package com.vald3nir.shoppinglist.core.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val SyncAdminUniqueKey: Long = 0

@Entity(tableName = "db_admin")
internal data class SyncAdminEntity(
    @PrimaryKey
    val id: Long = SyncAdminUniqueKey,
    val uuid: String? = null,
    val lastUpdated: Long = System.currentTimeMillis(),
    val productsVersion: Long?,
    val categoriesVersion: Long?,
)