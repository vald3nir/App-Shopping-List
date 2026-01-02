package com.vald3nir.shoppinglist.core.repository.sync

import com.vald3nir.shoppinglist.core.repository.database.dao.CategoryDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ProductsDao
import com.vald3nir.shoppinglist.core.repository.database.dao.SyncAdminDao
import com.vald3nir.shoppinglist.core.repository.database.entities.CategoryEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ProductEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.SyncAdminEntity
import com.vald3nir.shoppinglist.core.repository.sync.model.CategorySyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.DbAdminSyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.ProductSyncModel
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.utils.extensions.getCurrentDate
import com.vald3nir.toolkit.core.utils.extensions.orZero
import com.vald3nir.toolkit.core.utils.security.generateUUID
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

interface SyncProductsRepository {
    suspend fun checkDbSyncStatus()
    suspend fun syncProducts()
    suspend fun syncCategories()
}

internal class SyncProductsRepositoryImpl @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val supabaseClient: SupabaseClient,
    private val syncAdminDao: SyncAdminDao,
    private val productsDao: ProductsDao,
    private val categoryDao: CategoryDao,
) : SyncProductsRepository {

    private var flagDownloadProductTable = false
    private var flagDownloadCategoriesTable = false

    override suspend fun checkDbSyncStatus() {
        val syncAdminCloud = supabaseClient.from("db_admin").select().decodeSingle<DbAdminSyncModel>()
        val syncAdminLocal = syncAdminDao.getSyncAdmin()
        val cloudProdVer = syncAdminCloud.productsVersion.orZero()
        val cloudCatVer = syncAdminCloud.categoriesVersion.orZero()
        val localProdVer = syncAdminLocal?.productsVersion.orZero()
        val localCatVer = syncAdminLocal?.categoriesVersion.orZero()

        analyticsHelper.onLog("Sync Check | Products: C($cloudProdVer) L($localProdVer) | Categories: C($cloudCatVer) L($localCatVer)")
        flagDownloadProductTable = cloudProdVer > localProdVer
        flagDownloadCategoriesTable = cloudCatVer > localCatVer
        syncAdminDao.insertOrUpdate(SyncAdminEntity(uuid = syncAdminCloud.id, productsVersion = cloudProdVer, categoriesVersion = cloudCatVer))
    }

    override suspend fun syncProducts() {
        if (flagDownloadProductTable) {
            val products = supabaseClient.from("product").select().decodeList<ProductSyncModel>()
            analyticsHelper.onLog("Download ${products.size} products")
            productsDao.clearAndInsert(products.map {
                ProductEntity(
                    id = it.id ?: generateUUID(),
                    name = it.name,
                    createdAt = it.createdAt ?: getCurrentDate(),
                    categoryId = it.categoryId,
                    brandId = it.brandId
                )
            })
        }
    }

    override suspend fun syncCategories() {
        if (flagDownloadCategoriesTable) {
            val categories = supabaseClient.from("category").select().decodeList<CategorySyncModel>()
            analyticsHelper.onLog("Download ${categories.size} categories")
            categoryDao.clearAndInsert(categories.map {
                CategoryEntity(
                    id = it.id ?: generateUUID(),
                    name = it.name,
                    createdAt = it.createdAt ?: getCurrentDate(),
                    iconURL = it.iconURL,
                )
            })
        }
    }
}