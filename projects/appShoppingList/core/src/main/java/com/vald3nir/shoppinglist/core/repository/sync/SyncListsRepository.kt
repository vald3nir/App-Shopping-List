package com.vald3nir.shoppinglist.core.repository.sync

import com.vald3nir.shoppinglist.core.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.dao.UserDao
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.crossref.ShoppingListCrossRef
import com.vald3nir.shoppinglist.core.repository.sync.model.ItemListSyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.ListSyncModel
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.services.analytics.notifyLog
import com.vald3nir.toolkit.core.utils.extensions.orZero
import com.vald3nir.toolkit.core.utils.extensions.sanitize
import com.vald3nir.toolkit.core.utils.extensions.toDateReduced
import com.vald3nir.toolkit.core.utils.security.generateUUID
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

interface SyncListsRepository {
    suspend fun syncLists()
}

internal class SyncListsRepositoryImpl @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val supabaseClient: SupabaseClient,
    private val userDao: UserDao,
    private val shoppingListDao: ShoppingListDao,
    private val itemShoppingListDao: ItemShoppingListDao,
) : SyncListsRepository {

    override suspend fun syncLists() {
        try {
            val owner = userDao.getCurrentUser().email?.sanitize()
            if (owner.isNullOrEmpty()) return
            analyticsHelper.onLog("Sync Lists of $owner")

            // todo valdenir corrigir bug

            val listNotSync = shoppingListDao.selectListsNotSync()
            if (listNotSync.isNotEmpty()) {
                treatListsNotSync(owner, listNotSync)
                return
            }

            if (shoppingListDao.isEmpty()) {
                updateListsLocal(owner)
                updateListsCloud(owner)
                return
            }

            updateListsCloud(owner)

        } catch (e: Exception) {
            e.notifyLog()
            analyticsHelper.onLog("Sync lists failed: ${e.message}")
        }
    }

    private suspend fun treatListsNotSync(owner: String, listNotSync: List<ShoppingListCrossRef>) {
        analyticsHelper.onLog("Treat lists not sync")

        val listsCloud = listNotSync.map { it.shoppingList.toSyncModel(owner) }
        val itemsCloud = listNotSync.flatMap { it.items.toSyncModel(owner) }

        analyticsHelper.onLog("listsCloud: $listsCloud")
        analyticsHelper.onLog("itemsCloud: $itemsCloud")

        supabaseClient.from("shopping_list").upsert(listsCloud)
        supabaseClient.from("item_list").upsert(itemsCloud)
        analyticsHelper.onLog("Upload Cloud ${listsCloud.size} lists and ${itemsCloud.size} items")
    }

    private suspend fun updateListsLocal(owner: String) {
        analyticsHelper.onLog("Updating list local")

        val listsCloudJson = supabaseClient.from("shopping_list").select { filter { eq("owner", owner) } }
        analyticsHelper.onLog(listsCloudJson.data)
        val listsCloud = listsCloudJson.decodeList<ListSyncModel>()

        val itemsCloudJson = supabaseClient.from("item_list").select { filter { eq("owner", owner) } }
        analyticsHelper.onLog(itemsCloudJson.data)
        val itemsCloud = itemsCloudJson.decodeList<ItemListSyncModel>()

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

        supabaseClient.from("shopping_list").delete { filter { eq("owner", owner) } }
        supabaseClient.from("item_list").delete { filter { eq("owner", owner) } }
        analyticsHelper.onLog("Delete lists cloud")

        val listsLocal = shoppingListDao.selectAllListsWithItems()
        if (listsLocal.isEmpty()) return

        val listsCloud = listsLocal.map { it.shoppingList.toSyncModel(owner) }
        val itemsCloud = listsLocal.flatMap { it.items.toSyncModel(owner) }

        analyticsHelper.onLog("listsCloud: $listsCloud")
        analyticsHelper.onLog("itemsCloud: $itemsCloud")

        supabaseClient.from("shopping_list").upsert(listsCloud)
        supabaseClient.from("item_list").upsert(itemsCloud)
        analyticsHelper.onLog("Upload Cloud ${listsCloud.size} lists and ${itemsCloud.size} items")
    }
}

private fun ListSyncModel.toEntity() = ShoppingListEntity(
    id = this.listId,
    uuid = this.id,
    title = this.title,
    createdAt = this.createdAt.toDateReduced(),
)

private fun ItemListSyncModel.toEntity(listId: Long) = ItemShoppingListEntity(
    uuid = this.id,
    shoppingListId = listId,
    product = this.product,
    category = this.category,
    quantity = this.quantity.orZero(),
    unitPrice = this.unitPrice.orZero(),
    iconURL = this.iconURL,
    createdAt = this.createdAt.toDateReduced(),
)

private fun ShoppingListEntity.toSyncModel(owner: String) = ListSyncModel(
    id = this.uuid ?: generateUUID(),
    createdAt = this.createdAt,
    owner = owner,
    listId = this.id,
    title = this.title,
)

private fun List<ItemShoppingListEntity>.toSyncModel(owner: String): List<ItemListSyncModel> {
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