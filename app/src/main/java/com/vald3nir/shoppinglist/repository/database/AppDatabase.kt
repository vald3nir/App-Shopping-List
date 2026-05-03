package com.vald3nir.shoppinglist.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.repository.database.dao.ProductsDao
import com.vald3nir.shoppinglist.repository.database.dao.ShoppingListDao
import com.vald3nir.shoppinglist.repository.database.dao.UserDao
import com.vald3nir.shoppinglist.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.repository.database.entities.ProductEntity
import com.vald3nir.shoppinglist.repository.database.entities.ShoppingListEntity
import com.vald3nir.shoppinglist.repository.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ShoppingListEntity::class,
        ItemShoppingListEntity::class,
        ProductEntity::class,
    ],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getItemShoppingListDao(): ItemShoppingListDao
    abstract fun getShoppingListDao(): ShoppingListDao
    abstract fun getProductsDao(): ProductsDao
}