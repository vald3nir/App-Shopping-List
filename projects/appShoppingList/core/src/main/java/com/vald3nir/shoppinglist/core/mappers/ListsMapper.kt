package com.vald3nir.shoppinglist.core.mappers

import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.sync.model.ItemListSyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.ListSyncModel
import com.vald3nir.toolkit.core.utils.extensions.orZero
import com.vald3nir.toolkit.core.utils.extensions.toDateReduced
import com.vald3nir.toolkit.core.utils.security.generateUUID

internal fun ListSyncModel.toEntity() = ShoppingListEntity(
    id = this.listId,
    uuid = this.id,
    title = this.title,
    createdAt = this.createdAt.toDateReduced(),
)

internal fun ItemListSyncModel.toEntity(listId: Long) = ItemShoppingListEntity(
    uuid = this.id,
    shoppingListId = listId,
    product = this.product,
    category = this.category,
    quantity = this.quantity.orZero(),
    unitPrice = this.unitPrice.orZero(),
    iconURL = this.iconURL,
    createdAt = this.createdAt.toDateReduced(),
)

internal fun ShoppingListEntity.toSyncModel(owner: String) = ListSyncModel(
    id = this.uuid ?: generateUUID(),
    createdAt = this.createdAt,
    owner = owner,
    listId = this.id,
    title = this.title,
)

internal fun List<ItemShoppingListEntity>.toSyncModel(owner: String): List<ItemListSyncModel> {
    return this.map {
        ItemListSyncModel(
            id = it.uuid ?: generateUUID(),
            itemId = it.id,
            listId = it.shoppingListId,
            category = it.category,
            product = it.product,
            iconURL = it.iconURL,
            quantity = it.quantity,
            unitPrice = it.unitPrice,
            owner = owner,
            createdAt = it.createdAt
        )
    }
}