package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTO
import com.vald3nir.shoppinglist.domain.mapper.toEntity
import com.vald3nir.shoppinglist.repository.database.dao.ItemShoppingListDao
import com.vald3nir.toolkit.core.baseclasses.ParameterInvalidException
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class LoadItemListUseCase @Inject constructor(private val itemShoppingListDao: ItemShoppingListDao) {
    suspend fun loadItem(itemId: String?) = itemShoppingListDao.getItem(itemId)?.toDTO()
    fun loadItemFlow(itemId: String?) = itemShoppingListDao.getItemFlow(itemId).map { it?.toDTO() }
}

internal class LoadItemsByListUseCase @Inject constructor(private val itemShoppingListDao: ItemShoppingListDao) {
    fun execute(listId: String?) = itemShoppingListDao.getItemsByListFlow(listId).map { it.toDTO() }
}

internal class LoadTopFrequentsItemListsUseCase @Inject constructor(private val itemShoppingListDao: ItemShoppingListDao) {
    fun execute(listId: String?) = itemShoppingListDao.getTopFrequents(listID = listId, maxItems = 20).map {
        it.map { entity ->
            ItemShoppingListDTO(
                product = entity.product,
            )
        }
    }
}

internal class ChangeItemStatusOnShoppingCartUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val itemShoppingListDao: ItemShoppingListDao
) {

    suspend fun execute(itemId: String?) {
        analyticsHelper.onLog("Toggle item status with itemId: $itemId")
        if (itemId == null) throw ParameterInvalidException()
        itemShoppingListDao.toggleIsAdd(itemId)
    }
}

internal class AddNewItemListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val itemShoppingListDao: ItemShoppingListDao,
) {
    suspend fun execute(item: ItemShoppingListDTO) {
        analyticsHelper.onLog("insertNewItemList called with item: $item")
        itemShoppingListDao.insert(item.toEntity())
        itemShoppingListDao.removeDuplicates()
    }
}

internal class UpdateItemListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val itemShoppingListDao: ItemShoppingListDao,
) {

    suspend fun execute(item: ItemShoppingListDTO) {
        analyticsHelper.onLog("updateItem called with item: $item")
        itemShoppingListDao.insert(item.toEntity())
    }
}

internal class DeleteItemListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val itemShoppingListDao: ItemShoppingListDao,
) {
    suspend fun execute(itemId: String?) {
        analyticsHelper.onLog("delete item with id: $itemId")
        if (itemId == null) throw ParameterInvalidException()
        itemShoppingListDao.fakeDeleteItem(itemId)
    }
}