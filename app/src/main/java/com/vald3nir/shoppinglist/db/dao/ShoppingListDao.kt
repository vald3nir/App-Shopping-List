package com.vald3nir.shoppinglist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItemsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    // ========================================================================================
    // INSERT FUNCTIONS
    // ========================================================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(entity: ShoppingListModal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(entities: List<ItemShoppingListModal>): List<Long>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ItemShoppingListModal): Long

    @Transaction
    suspend fun cleanAndInsert(models: List<ShoppingListWithItemsModel>) {
        deleteAllLists()
        deleteAllItems()
        models.forEach {
            val shoppingListId = insertShoppingList(it.shoppingList)
            insertItems(it.items.map { item -> item.copy(shoppingListId = shoppingListId) })
        }
    }

    // ========================================================================================
    // UPDATE FUNCTIONS
    // ========================================================================================

    @Query("UPDATE ${ShoppingListModal.TABLE_NAME} SET title = :newTitle WHERE id = :shoppingListId")
    suspend fun updateShoppingListTitle(shoppingListId: Long, newTitle: String)

    // ========================================================================================
    // DELETE FUNCTIONS
    // ========================================================================================

    @Query("DELETE FROM ${ShoppingListModal.TABLE_NAME} WHERE id = :shoppingListId")
    suspend fun deleteShoppingListById(shoppingListId: Long)

    @Query("DELETE FROM ${ItemShoppingListModal.TABLE_NAME} WHERE shoppingListId = :shoppingListId")
    suspend fun deleteItemsByShoppingListId(shoppingListId: Long)

    @Query("DELETE FROM ${ItemShoppingListModal.TABLE_NAME} WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Long)

    @Query("DELETE FROM ${ItemShoppingListModal.TABLE_NAME}")
    suspend fun deleteAllItems()

    @Query("DELETE FROM ${ShoppingListModal.TABLE_NAME}")
    suspend fun deleteAllLists()

    // ========================================================================================
    // QUERY FUNCTIONS
    // ========================================================================================

    @Query("SELECT COUNT(*) = 0 FROM ${ShoppingListModal.TABLE_NAME}")
    suspend fun isEmpty(): Boolean

    @Transaction
    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME} WHERE id = :shoppingListId")
    fun selectShoppingListFlow(shoppingListId: Long?): Flow<ShoppingListWithItemsModel?>

    @Query("SELECt * FROM ${ItemShoppingListModal.TABLE_NAME} WHERE id = :itemId")
    fun selectItemShoppingListFlow(itemId: Long?): Flow<ItemShoppingListModal?>

    @Transaction
    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME} WHERE id = :shoppingListId")
    suspend fun selectShoppingList(shoppingListId: Long?): ShoppingListWithItemsModel?

    @Transaction
    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME}")
    suspend fun loadAllListsWithItems(): List<ShoppingListWithItemsModel?>

    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME} ORDER BY lastUpdated DESC")
    fun selectAllShoppingLists(): Flow<List<ShoppingListModal>>

    @Query(
        """
            SELECT * FROM ${ShoppingListModal.TABLE_NAME}
            WHERE title LIKE '%' || :query || '%' OR date LIKE '%' || :query || '%'
        """
    )
    fun searchShoppingLists(query: String): Flow<List<ShoppingListModal>>
}
