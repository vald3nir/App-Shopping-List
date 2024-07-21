package com.vald3nir.shoppinglist.repository.usecases


import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
import com.vald3nir.android.firebase.utils.parseEmailToKey
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItems
import com.vald3nir.shoppinglist.domain.mapper.toProductsModel
import com.vald3nir.shoppinglist.domain.mapper.toShoppingListWithItemsModel

object FirebaseUseCase {

    private fun getKey() = FirebaseAuthenticator.getFirebaseUser()?.email?.parseEmailToKey()

    suspend fun importShoppingLists(dao: ShoppingListDao) {
        kotlin.runCatching {
            getKey()?.let { key ->
                val response = FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/clients/$key/shopping_lists")
                val models = response.toShoppingListWithItemsModel()
                dao.cleanAndInsert(models)
            }
        }
    }

    suspend fun exportShoppingLists(dao: ShoppingListDao) {
        kotlin.runCatching {
            getKey()?.let { key ->
                val shoppingLists: List<ShoppingListWithItems?> = dao.loadAllListsWithItems()
                FirebaseDB.insertOrUpdate(path = "/${BuildConfig.FLAVOR}/clients/$key/shopping_lists", data = shoppingLists)
            }
        }
    }

    suspend fun importProducts(dao: ProductsDao) {
        kotlin.runCatching {
            val response = FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/products").toProductsModel()
            dao.clearAndInsert(response)
        }
    }
}