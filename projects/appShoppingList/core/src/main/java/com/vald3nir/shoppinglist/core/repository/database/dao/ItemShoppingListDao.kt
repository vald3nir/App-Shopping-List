package com.vald3nir.shoppinglist.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ItemShoppingListDao {

    // --- Create / Insert / Update ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ItemShoppingListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(entities: List<ItemShoppingListEntity>): List<Long>

    @Query(
        """
        UPDATE item_shopping_list
        SET isAdd = NOT isAdd
        WHERE id = :itemId
        """
    )
    suspend fun toggleIsAdd(itemId: Long)

    // --- Read / Queries ---

    @Query(
        """
        SELECT * FROM item_shopping_list
        WHERE shoppingListId = :listId 
        ORDER BY category ASC
        """
    )
    fun getItemsByListFlow(listId: Long): Flow<List<ItemShoppingListEntity>>

    @Query(
        """
        SELECT * FROM item_shopping_list
        WHERE id = :itemId         
        """
    )
    fun getItemFlow(itemId: Long): Flow<ItemShoppingListEntity?>

    // --- Delete ---

    @Query("DELETE FROM item_shopping_list WHERE id = :itemId")
    suspend fun deleteItem(itemId: Long)

    @Query("DELETE FROM item_shopping_list")
    suspend fun deleteAll()
}