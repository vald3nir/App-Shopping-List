package com.vald3nir.shoppinglist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ProductModel
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal

@Database(
    entities = [
        ShoppingListModal::class,
        ItemShoppingListModal::class,
        ProductModel::class,
    ],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getShoppingListDao(): ShoppingListDao
    abstract fun getProductsDao(): ProductsDao
}