package com.vald3nir.shoppinglist.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.core.utils.extensions.getISODate

internal const val UserUniqueKey: Long = 0

@Entity(tableName = "user")
internal data class UserEntity(
    @PrimaryKey
    val id: Long = UserUniqueKey,
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val updatedAt: String = getISODate(),
)