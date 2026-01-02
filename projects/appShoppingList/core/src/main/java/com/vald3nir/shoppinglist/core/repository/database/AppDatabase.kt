package com.vald3nir.shoppinglist.core.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vald3nir.shoppinglist.core.BuildConfig
import com.vald3nir.shoppinglist.core.repository.database.dao.CategoryDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ProductsDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.dao.SyncAdminDao
import com.vald3nir.shoppinglist.core.repository.database.dao.UserDao
import com.vald3nir.shoppinglist.core.repository.database.entities.CategoryEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ProductEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.SyncAdminEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.UserEntity

@Database(
    entities = [
        SyncAdminEntity::class,
        UserEntity::class,
        ShoppingListEntity::class,
        ItemShoppingListEntity::class,
        ProductEntity::class,
        CategoryEntity::class,
    ],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getSyncAdminDao(): SyncAdminDao
    abstract fun getUserDao(): UserDao
    abstract fun getItemShoppingListDao(): ItemShoppingListDao
    abstract fun getShoppingListDao(): ShoppingListDao
    abstract fun getProductsDao(): ProductsDao
    abstract fun getCategoryDao(): CategoryDao
}