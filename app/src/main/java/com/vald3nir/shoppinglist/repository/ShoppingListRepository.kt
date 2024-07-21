package com.vald3nir.shoppinglist.repository

import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTOList
import com.vald3nir.shoppinglist.domain.mapper.toModal
import com.vald3nir.shoppinglist.domain.mapper.toNewModal
import com.vald3nir.shoppinglist.repository.usecases.FirebaseUseCase
import com.vald3nir.shoppinglist.repository.usecases.importShoppingLists
import com.vald3nir.shoppinglist.repository.usecases.insertOrUpdateShoppingList
import com.vald3nir.shoppinglist.repository.usecases.loadItemShoppingListFlow
import com.vald3nir.shoppinglist.repository.usecases.loadShoppingList
import com.vald3nir.shoppinglist.repository.usecases.loadShoppingListFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ShoppingListRepository {
    fun getAll(): Flow<List<ShoppingListDTO>>
    fun searchShoppingLists(query: String): Flow<List<ShoppingListDTO>>
    fun loadShoppingListFlow(id: Long?): Flow<ShoppingListDTO?>
    fun loadItemShoppingListFlow(id: Long?): Flow<ItemShoppingListDTO?>

    suspend fun addNewItem(newItem: ItemShoppingListDTO)
    suspend fun saveShoppingList(dto: ShoppingListDTO)
    suspend fun cloneShoppingList(dto: ShoppingListDTO)
    suspend fun updateShoppingListTitle(shoppingListId: Long, newTitle: String)
    suspend fun updateItemShoppingList(item: ItemShoppingListDTO): ShoppingListDTO?

    suspend fun deleteItemShoppingList(itemId: Long)
    suspend fun deleteShoppingList(id: Long)
}

class ShoppingListRepositoryImpl @Inject constructor(private val dao: ShoppingListDao) : ShoppingListRepository {

    override fun getAll(): Flow<List<ShoppingListDTO>> = dao.importShoppingLists()

    override fun searchShoppingLists(query: String): Flow<List<ShoppingListDTO>> = dao.searchShoppingLists(query).map { it.toDTOList() }

    override fun loadShoppingListFlow(id: Long?) = dao.loadShoppingListFlow(id)

    override fun loadItemShoppingListFlow(id: Long?) = dao.loadItemShoppingListFlow(id)

    override suspend fun addNewItem(newItem: ItemShoppingListDTO) {
        dao.insert(newItem.toNewModal())
    }

    override suspend fun saveShoppingList(dto: ShoppingListDTO) {
        dao.insertOrUpdateShoppingList(dto)
        FirebaseUseCase.exportShoppingLists(dao)
    }

    override suspend fun cloneShoppingList(dto: ShoppingListDTO) {
        saveShoppingList(dto.copy(id = null, title = "Cópia de ${dto.title}"))
    }

    override suspend fun updateShoppingListTitle(shoppingListId: Long, newTitle: String) {
        dao.updateShoppingListTitle(shoppingListId, newTitle)
        FirebaseUseCase.exportShoppingLists(dao)
    }

    override suspend fun updateItemShoppingList(item: ItemShoppingListDTO): ShoppingListDTO? {
        dao.insert(item.toModal())
        FirebaseUseCase.exportShoppingLists(dao)
        return dao.loadShoppingList(item.shoppingListId)
    }

    override suspend fun deleteItemShoppingList(itemId: Long) {
        dao.deleteItemById(itemId)
        FirebaseUseCase.exportShoppingLists(dao)
    }

    override suspend fun deleteShoppingList(id: Long) {
        dao.deleteShoppingListById(id)
        FirebaseUseCase.exportShoppingLists(dao)
    }
}