package com.vald3nir.shoppinglist.repository

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import kotlinx.coroutines.flow.Flow

internal interface AppRepository {

    // Sync
    suspend fun downloadProducts()
    suspend fun syncLists()

    // Products
    suspend fun searchProductName(barcode: String?): String?
    fun getProductNames(): Flow<List<String>>

    // Shopping lists
    fun getShoppingListFlow(listId: String): Flow<ShoppingListDTO>
    fun getShoppingLists(): Flow<List<ShoppingListDTO>>
    fun openListEditing(): Flow<ShoppingListDTO>
    suspend fun closeListEditing(listId: String?, title: String?)
    suspend fun cloneShoppingList(listId: String?)
    suspend fun deleteShoppingList(listId: String?)
    suspend fun deleteLists()

    // Items
    suspend fun insertNewItemList(item: ItemShoppingListDTO)
    fun getItemFlow(itemId: String): Flow<ItemShoppingListDTO?>
    suspend fun getItem(itemId: String?): ItemShoppingListDTO?
    fun getItemsByListFlow(listId: String): Flow<List<ItemShoppingListDTO>>
    fun loadTopFrequentsItemLists(listId: String?): Flow<List<ItemShoppingListDTO>>
    suspend fun toggleIsAdd(itemId: String?)
    suspend fun updateItem(item: ItemShoppingListDTO)
    suspend fun removeItem(itemId: String?)
}