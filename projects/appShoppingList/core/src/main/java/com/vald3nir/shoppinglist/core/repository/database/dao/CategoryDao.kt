package com.vald3nir.shoppinglist.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.core.repository.database.entities.CategoryEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.crossref.ProductWithCategoryCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CategoryDao {

    // --- Create / Update ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<CategoryEntity>): List<Long>

    // --- Read / Queries ---

    @Query("SELECT * FROM category ORDER BY name ASC")
    fun getCategoriesFlow(): Flow<List<CategoryEntity>>

    @Query("SELECT COUNT(*) = 0 FROM category")
    suspend fun isEmpty(): Boolean

    @Query(
        """
        SELECT 
            p.name AS productName,
            c.name AS categoryName,
            c.iconURL AS iconURL
        FROM products p
        LEFT JOIN category c ON c.id = p.categoryId
        WHERE p.name = :productName
        LIMIT 1
    """
    )
    suspend fun getProductWithCategoryByName(productName: String): ProductWithCategoryCrossRef?

    // --- Delete ---

    @Query("DELETE FROM category")
    suspend fun deleteAll()

    // --- Transactions ---

    @Transaction
    suspend fun clearAndInsert(entities: List<CategoryEntity>) {
        deleteAll()
        insert(entities)
    }
}