package com.vald3nir.shoppinglist.core.repository

import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.core.repository.database.dao.ShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.toolkit.core.baseclasses.ParameterInvalidException
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ShoppingListRepository {
    fun getShoppingListFlow(listId: Long): Flow<ShoppingListDTO>
    fun getShoppingLists(): Flow<List<ShoppingListDTO>>
    fun openListEditing(): Flow<ShoppingListDTO>
    suspend fun closeListEditing(listId: Long?, title: String?)
    suspend fun cloneShoppingList(listId: Long?)
    suspend fun deleteShoppingList(listId: Long?)
    suspend fun deleteLists()
}

internal class ShoppingListRepositoryImpl @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val shoppingListDao: ShoppingListDao,
) : ShoppingListRepository {

    override fun getShoppingListFlow(listId: Long) = shoppingListDao.getShoppingListFlow(listId).map {
        ShoppingListDTO(
            id = it?.id,
            title = it?.title,
            date = it?.createdAt,
        )
    }

    override suspend fun closeListEditing(listId: Long?, title: String?) {
        analyticsHelper.onLog("close List Editing called with listId: $listId, title: $title")
        if (listId == null || title == null) throw ParameterInvalidException()
        shoppingListDao.closeEditionShoppingList(shoppingListId = listId, title = title)
    }

    override fun getShoppingLists(): Flow<List<ShoppingListDTO>> = shoppingListDao.selectShoppingListsFlow().map { entities ->
        entities?.map { ShoppingListDTO(it.id, it.title, it.createdAt) }.orEmpty()
    }

    override suspend fun cloneShoppingList(listId: Long?) {
        analyticsHelper.onLog("clone list with listId: $listId")
        if (listId == null) throw ParameterInvalidException()
        shoppingListDao.cloneShoppingList(listId)
    }

    override suspend fun deleteShoppingList(listId: Long?) {
        analyticsHelper.onLog("delete list with listId: $listId")
        if (listId == null) throw ParameterInvalidException()
        shoppingListDao.deleteList(listId)
    }

    override suspend fun deleteLists() {
        analyticsHelper.onLog("delete all lists")
        shoppingListDao.deleteAll()
    }

    override fun openListEditing(): Flow<ShoppingListDTO> = flow {
        analyticsHelper.onLog("openListEditing called")
        val id = shoppingListDao.openListInEditMode()
        emit(id)
    }.flatMapLatest { listId ->
        shoppingListDao.selectShoppingListWithItemsFlow(listId).map { list ->
            ShoppingListDTO(
                id = list?.shoppingList?.id,
                title = list?.shoppingList?.title,
                date = list?.shoppingList?.createdAt,
                items = list?.items?.toDTO() ?: emptyList()
            )
        }
    }

    private fun List<ItemShoppingListEntity>.toDTO() = this.map {
        ItemShoppingListDTO(
            id = it.id,
            shoppingListId = it.shoppingListId,
            category = it.category,
            quantity = it.quantity,
            product = it.product,
            iconURL = it.iconURL,
            unitPrice = it.unitPrice,
            isAdd = it.isAdd
        )
    }
}