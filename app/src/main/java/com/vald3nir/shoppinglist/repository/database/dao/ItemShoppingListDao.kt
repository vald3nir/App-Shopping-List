package com.vald3nir.shoppinglist.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.repository.database.entities.ItemShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ItemShoppingListDao {

    // --- Create / Insert / Update ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ItemShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(entities: List<ItemShoppingListEntity>)

    @Query(
        """
        UPDATE item_shopping_list
        SET onCart = NOT onCart
        WHERE id = :itemId
        """
    )
    suspend fun toggleIsAdd(itemId: String)

    // --- Read / Queries ---

    @Query(
        """
        SELECT * FROM item_shopping_list
        WHERE shoppingListId = :listId 
        ORDER BY product ASC
        """
    )
    fun getItemsByListFlow(listId: String?): Flow<List<ItemShoppingListEntity>>

    @Query(
        """
        SELECT * FROM item_shopping_list
        WHERE id = :itemId         
        """
    )
    fun getItemFlow(itemId: String?): Flow<ItemShoppingListEntity?>

    @Query(
        """
        SELECT * FROM item_shopping_list
        WHERE id = :itemId         
        """
    )
    suspend fun getItem(itemId: String?): ItemShoppingListEntity?

    @Query(
        """
        SELECT * 
        FROM item_shopping_list 
        WHERE product IS NOT NULL AND product != '' and shoppingListId != :listID
        GROUP BY product 
        ORDER BY COUNT(product) DESC 
        LIMIT :maxItems
    """
    )
    fun getTopFrequents(listID: String?, maxItems: Int): Flow<List<ItemShoppingListEntity>>

    // --- Delete ---

    @Query(
        """
        SELECT id FROM item_shopping_list 
        WHERE id NOT IN (
            SELECT MIN(id) 
            FROM item_shopping_list 
            GROUP BY shoppingListId, product
        )
    """
    )
    suspend fun getDuplicateItemIds(): List<String>

    @Transaction
    suspend fun removeDuplicates() {
        val duplicateIds = getDuplicateItemIds()
        if (duplicateIds.isNotEmpty()) {
            deleteItemsByIds(duplicateIds)
        }
    }

    @Query("DELETE FROM item_shopping_list WHERE id IN (:ids)")
    suspend fun deleteItemsByIds(ids: List<String>)

    @Query("DELETE FROM item_shopping_list WHERE id = :itemId")
    suspend fun deleteItem(itemId: String)


    @Query("DELETE FROM item_shopping_list WHERE shoppingListId = :listId")
    suspend fun deleteItemsByList(listId: String)

    @Query("DELETE FROM item_shopping_list")
    suspend fun deleteAll()

}