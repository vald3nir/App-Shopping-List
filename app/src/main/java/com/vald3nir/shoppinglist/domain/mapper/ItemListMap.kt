package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.toolkit.core.utils.security.generateUUID

internal fun List<ItemShoppingListEntity>.toDTO() = this.map { it.toDTO() }

internal fun ItemShoppingListEntity.toDTO() = ItemShoppingListDTO(
    id = id,
    shoppingListId = shoppingListId,
    product = product,
    quantity = quantity,
    unitPrice = unitPrice,
    onCart = onCart
)

internal fun ItemShoppingListDTO.toEntity() = ItemShoppingListEntity(
    id = id ?: generateUUID(),
    shoppingListId = shoppingListId,
    quantity = quantity,
    product = product,
    unitPrice = unitPrice,
    onCart = onCart
)