package com.vald3nir.shoppinglist.repository

import android.content.Context
import com.vald3nir.android.firebase.utils.notifyLog
import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.domain.mapper.toModel
import com.vald3nir.shoppinglist.repository.usecases.FirebaseUseCase
import com.vald3nir.shoppinglist.repository.usecases.importProductsFromDataset
import com.vald3nir.shoppinglist.repository.usecases.importShoppingListFromMock
import javax.inject.Inject

interface ImportDataRepository {
    suspend fun importShoppingListFromServer()
    suspend fun importShoppingListFromLocal()
    suspend fun importProductsFromServer(context: Context)
    suspend fun importProductsFromLocal(context: Context)
}

class ImportDataRepositoryImpl @Inject constructor(private val shoppingListDao: ShoppingListDao, private val productsDao: ProductsDao) : ImportDataRepository {

    override suspend fun importShoppingListFromServer() {
        kotlin.runCatching {
            if (shoppingListDao.isEmpty()) {
                val response = FirebaseUseCase.importShoppingLists()
                shoppingListDao.cleanAndInsert(response)
            }
        }.onFailure {
            it.notifyLog()
        }
    }

    override suspend fun importShoppingListFromLocal() {
        shoppingListDao.importShoppingListFromMock()
    }

    override suspend fun importProductsFromServer(context: Context) {
        runCatching {
            val response = FirebaseUseCase.importProducts()
            productsDao.clearAndInsert(response)
        }.onFailure {
            it.notifyLog()
            importProductsFromLocal(context)
        }

    }

    override suspend fun importProductsFromLocal(context: Context) {
        productsDao.clearAndInsert(context.importProductsFromDataset().toModel())
    }

}