package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTO
import com.vald3nir.shoppinglist.domain.mapper.toEntity
import com.vald3nir.shoppinglist.repository.api.FirebaseDataSource
import com.vald3nir.shoppinglist.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.repository.database.dao.ShoppingListDao
import com.vald3nir.shoppinglist.repository.database.dao.UserDao
import com.vald3nir.shoppinglist.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.repository.database.entities.ShoppingListEntity
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.utils.extensions.sanitize
import javax.inject.Inject

internal class SyncListsUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val userDao: UserDao,
    private val shoppingListDao: ShoppingListDao,
    private val itemShoppingListDao: ItemShoppingListDao,
    private val firebaseDataSource: FirebaseDataSource,
) {
    suspend fun execute() {
        val owner = userDao.getCurrentUser().email?.sanitize()
        if (owner.isNullOrEmpty()) return

        val listsCloud = firebaseDataSource.downloadLists(owner)

        analyticsHelper.onLog("Syncing lists")

        val listsLocal = shoppingListDao.selectAllListsWithItems()
        val deletedLocalListIds = shoppingListDao.selectMarkedDeletedListIds().toSet()
        val filteredCloudLists = listsCloud.filterNot { it.id != null && it.id in deletedLocalListIds }
        val (listsCloudEntities, itemsCloudEntities) = filteredCloudLists.splitToEntity()

        val mergedLists: List<ShoppingListEntity> = mergeShoppingLists(listsLocal.map { it.shoppingList }, listsCloudEntities)
        val mergedItems: List<ItemShoppingListEntity> = mergeItemsShoppingLists(listsLocal.flatMap { it.items }, itemsCloudEntities)

        shoppingListDao.insertShoppingLists(mergedLists)
        shoppingListDao.insertItems(mergedItems)

        itemShoppingListDao.deleteAllFakes()
        shoppingListDao.deleteAllFakes()

        firebaseDataSource.uploadLists(owner, shoppingListDao.selectAllListsWithItems().toDTO())
    }

    private fun mergeShoppingLists(local: List<ShoppingListEntity>, cloud: List<ShoppingListEntity>): List<ShoppingListEntity> = (local + cloud).distinctBy { it.id }

    private fun mergeItemsShoppingLists(local: List<ItemShoppingListEntity>, cloud: List<ItemShoppingListEntity>): List<ItemShoppingListEntity> = (local + cloud).distinctBy { it.id }


    private fun List<ShoppingListDTO>.splitToEntity(): Pair<List<ShoppingListEntity>, List<ItemShoppingListEntity>> {
        val shoppingListsEntities = this.map { it.toEntity() }
        val itemsEntities = this.flatMap { dtoList ->
            dtoList.items.map { itemDto -> itemDto.toEntity() }
        }
        return Pair(shoppingListsEntities, itemsEntities)
    }
}