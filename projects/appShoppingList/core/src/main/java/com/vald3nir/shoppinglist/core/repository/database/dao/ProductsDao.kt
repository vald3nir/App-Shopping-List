package com.vald3nir.shoppinglist.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.core.repository.database.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ProductsDao {

    // --- Create / Insert ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ProductEntity>): List<Long>

    // --- Read / Queries ---

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :name || '%' LIMIT 10")
    suspend fun getProductsByName(name: String): List<ProductEntity>

    @Query(
        """
        SELECT DISTINCT name 
        FROM products 
        WHERE name IS NOT NULL
        ORDER BY name
        """
    )
    fun getDistinctProductNames(): Flow<List<String>>

    @Query("SELECT COUNT(*) = 0 FROM products")
    suspend fun isEmpty(): Boolean

    // --- Delete ---

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    // --- Transactions ---

    @Transaction
    suspend fun clearAndInsert(entities: List<ProductEntity>) {
        deleteAll()
        insert(entities)
    }
}