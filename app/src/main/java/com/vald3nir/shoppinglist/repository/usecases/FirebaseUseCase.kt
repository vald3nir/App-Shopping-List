package com.vald3nir.shoppinglist.repository.usecases


import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
import com.vald3nir.android.firebase.utils.notifyLog
import com.vald3nir.android.firebase.utils.parseEmailToKey
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItemsModel
import com.vald3nir.shoppinglist.domain.mapper.toProductsModel
import com.vald3nir.shoppinglist.domain.mapper.toShoppingListWithItemsModel

object FirebaseUseCase {

    private fun getKey() = FirebaseAuthenticator.getFirebaseUser()?.email?.parseEmailToKey()

    suspend fun importShoppingLists(): List<ShoppingListWithItemsModel> {
        return getKey()?.let { key ->
            FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/clients/$key/shopping_lists").toShoppingListWithItemsModel()
        } ?: emptyList()
    }

    suspend fun importProducts() = FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/products").toProductsModel()

    fun exportShoppingLists(shoppingLists: List<ShoppingListWithItemsModel?>) {
        kotlin.runCatching {
            getKey()?.let { key ->
                FirebaseDB.insertOrUpdate(path = "/${BuildConfig.FLAVOR}/clients/$key/shopping_lists", data = shoppingLists)
            }
        }.onFailure { it.notifyLog() }
    }
}