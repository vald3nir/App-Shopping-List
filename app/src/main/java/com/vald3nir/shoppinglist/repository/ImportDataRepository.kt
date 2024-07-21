package com.vald3nir.shoppinglist.repository

import android.content.Context
import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.domain.mapper.toModel
import com.vald3nir.shoppinglist.repository.usecases.FirebaseUseCase
import com.vald3nir.shoppinglist.repository.usecases.importProductsFromDataset
import com.vald3nir.shoppinglist.repository.usecases.importShoppingListFromMock
import javax.inject.Inject

interface ImportDataRepository {
    suspend fun importShoppingListFromServer(forceUpdate: Boolean = false)
    suspend fun importShoppingListFromLocal()
    suspend fun importProductsFromServer(context: Context, forceUpdate: Boolean = false)
    suspend fun importProductsFromLocal(context: Context)
}

class ImportDataRepositoryImpl @Inject constructor(private val shoppingListDao: ShoppingListDao, private val productsDao: ProductsDao) : ImportDataRepository {

    override suspend fun importShoppingListFromServer(forceUpdate: Boolean) {
        kotlin.runCatching {
            if (forceUpdate || shoppingListDao.isEmpty()) {
                FirebaseUseCase.importShoppingLists(shoppingListDao)
            }
        }
    }

    override suspend fun importShoppingListFromLocal() {
        shoppingListDao.importShoppingListFromMock()
    }

    override suspend fun importProductsFromServer(context: Context, forceUpdate: Boolean) {
        if (forceUpdate || productsDao.isEmpty()) {
            kotlin.runCatching {
                FirebaseUseCase.importProducts(productsDao)
            }.onFailure {
                importProductsFromLocal(context)
            }
        }
    }

    override suspend fun importProductsFromLocal(context: Context) {
        productsDao.clearAndInsert(context.importProductsFromDataset().toModel())
    }

}