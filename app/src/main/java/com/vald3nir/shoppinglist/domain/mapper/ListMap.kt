package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.repository.database.entities.ShoppingListCrossRef
import com.vald3nir.shoppinglist.repository.database.entities.ShoppingListEntity
import com.vald3nir.toolkit.core.utils.extensions.getISODate
import com.vald3nir.toolkit.core.utils.security.generateUUID

internal fun List<ShoppingListCrossRef>.toDTO() = this.map { it.toDTO() }

internal fun ShoppingListCrossRef.toDTO() = ShoppingListDTO(
    id = shoppingList.id,
    title = shoppingList.title,
    createdAt = shoppingList.createdAt,
    inEditing = shoppingList.inEditing,
    items = items.map { it.toDTO() }
)


internal fun ShoppingListDTO.toEntity() = ShoppingListEntity(
    id = id ?: generateUUID(),
    title = title,
    createdAt = createdAt ?: getISODate(),
    inEditing = inEditing,
)