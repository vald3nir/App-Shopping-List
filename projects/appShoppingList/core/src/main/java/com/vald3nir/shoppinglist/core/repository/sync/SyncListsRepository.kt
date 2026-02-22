package com.vald3nir.shoppinglist.core.repository.sync

import com.vald3nir.shoppinglist.core.mappers.toEntity
import com.vald3nir.shoppinglist.core.mappers.toSyncModel
import com.vald3nir.shoppinglist.core.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.dao.UserDao
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.crossref.ShoppingListCrossRef
import com.vald3nir.shoppinglist.core.repository.sync.datasources.ListsCloudDataSource
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.services.analytics.notifyLog
import com.vald3nir.toolkit.core.utils.extensions.sanitize
import javax.inject.Inject

interface SyncListsRepository {
    suspend fun syncLists()
}

internal class SyncListsRepositoryImpl @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val userDao: UserDao,
    private val shoppingListDao: ShoppingListDao,
    private val itemShoppingListDao: ItemShoppingListDao,
    private val listsCloudDataSource: ListsCloudDataSource,
) : SyncListsRepository {

    override suspend fun syncLists() {
        try {
            val owner = userDao.getCurrentUser().email?.sanitize()
            if (owner.isNullOrEmpty()) return
            analyticsHelper.onLog("Sync Lists of $owner")

            val listsDeleted: List<ShoppingListEntity> = shoppingListDao.getDeletedLists()
            if (listsDeleted.isNotEmpty()) {
                treatListsDeleted(owner, listsDeleted)
            }

            val listNotSync = shoppingListDao.selectListsNotSync()
            if (listNotSync.isNotEmpty()) {
                treatListsNotSync(owner, listNotSync)
                updateListsLocal(owner)
                return
            }

            if (shoppingListDao.isEmpty()) {
                updateListsLocal(owner)
                updateListsCloud(owner)
                return
            }

            updateListsCloud(owner)
            updateListsLocal(owner)

        } catch (e: Exception) {
            e.notifyLog()
            analyticsHelper.onLog("Sync lists failed: ${e.message}")
        }
    }

    private suspend fun treatListsDeleted(owner: String, listsDeleted: List<ShoppingListEntity>) {
        analyticsHelper.onLog("Treat lists deleted")

        val listIds: List<Long> = listsDeleted.mapNotNull { it.id }
        analyticsHelper.onLog("Lists: $listIds")

        listsCloudDataSource.deleteAllLists(owner, listIds)
        listsCloudDataSource.deleteAllItems(owner, listIds)
        shoppingListDao.clearDeletedLists()
    }

    private suspend fun treatListsNotSync(owner: String, listNotSync: List<ShoppingListCrossRef>) {
        analyticsHelper.onLog("Treat lists not sync")

        val listsCloud = listNotSync.map { it.shoppingList.toSyncModel(owner) }
        val itemsCloud = listNotSync.flatMap { it.items.toSyncModel(owner) }

        analyticsHelper.onLog("listsCloud: $listsCloud")
        analyticsHelper.onLog("itemsCloud: $itemsCloud")

        listsCloudDataSource.addLists(listsCloud)
        listsCloudDataSource.addItemsList(itemsCloud)

        analyticsHelper.onLog("Upload Cloud ${listsCloud.size} lists and ${itemsCloud.size} items")
    }

    private suspend fun updateListsLocal(owner: String) {
        analyticsHelper.onLog("Updating list local")
        val listsCloud = listsCloudDataSource.getLists(owner)
        val itemsCloud = listsCloudDataSource.getItems(owner)

        shoppingListDao.deleteAll()
        itemShoppingListDao.deleteAll()
        analyticsHelper.onLog("Delete lists local")

        listsCloud.forEach { listModel ->
            val listId: Long = shoppingListDao.insertShoppingList(listModel.toEntity())
            val itemsToInsert: List<ItemShoppingListEntity> = itemsCloud.filter { it.listId == listModel.listId }.map { it.toEntity(listId) }
            if (itemsToInsert.isNotEmpty()) {
                itemShoppingListDao.insertItems(itemsToInsert)
            }
        }

        analyticsHelper.onLog("Update lists local with ${listsCloud.size} lists and ${itemsCloud.size} items")
    }

    private suspend fun updateListsCloud(owner: String) {
        analyticsHelper.onLog("Updating list cloud")

        listsCloudDataSource.deleteAllLists(owner)
        listsCloudDataSource.deleteAllItems(owner)
        analyticsHelper.onLog("Delete lists cloud")

        val listsLocal = shoppingListDao.selectAllListsWithItems()
        if (listsLocal.isEmpty()) return

        val listsCloud = listsLocal.map { it.shoppingList.toSyncModel(owner) }
        val itemsCloud = listsLocal.flatMap { it.items.toSyncModel(owner) }
        analyticsHelper.onLog("listsCloud: $listsCloud")
        analyticsHelper.onLog("itemsCloud: $itemsCloud")

        listsCloudDataSource.addLists(listsCloud)
        listsCloudDataSource.addItemsList(itemsCloud)
        analyticsHelper.onLog("Upload Cloud ${listsCloud.size} lists and ${itemsCloud.size} items")
    }
}