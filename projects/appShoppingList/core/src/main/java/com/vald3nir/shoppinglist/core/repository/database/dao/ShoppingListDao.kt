package com.vald3nir.shoppinglist.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.ShoppingListEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.crossref.ShoppingListCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ShoppingListDao {

    // --- Create / Insert / Update ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(entity: ShoppingListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingLists(entities: List<ShoppingListEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemShoppingListEntity>)

    @Query(
        """
        UPDATE shopping_list 
        SET title = :title,
            lastUpdated = :lastUpdated,
            inEditing = 0
        WHERE id = :shoppingListId
    """
    )
    suspend fun closeEditionShoppingList(
        shoppingListId: Long,
        title: String?,
        lastUpdated: Long = System.currentTimeMillis()
    )

    // --- Read / Queries ---

    @Query("SELECT * FROM shopping_list WHERE inEditing = 0 AND deleted = 0 ORDER BY lastUpdated DESC")
    fun selectShoppingListsFlow(): Flow<List<ShoppingListEntity>?>

    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    fun getShoppingListFlow(listId: Long): Flow<ShoppingListEntity?>

    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    fun selectShoppingListWithItemsFlow(listId: Long?): Flow<ShoppingListCrossRef?>

    @Query("SELECT * FROM shopping_list WHERE deleted = 1 ORDER BY lastUpdated DESC")
    suspend fun getDeletedLists(): List<ShoppingListEntity>

    @Query("SELECT COUNT(*) = 0 FROM shopping_list")
    suspend fun isEmpty(): Boolean

    @Query("SELECT * FROM shopping_list WHERE uuid IS NULL")
    suspend fun selectListsNotSync(): List<ShoppingListCrossRef>

    @Query("SELECT * FROM shopping_list WHERE inEditing = 1 LIMIT 1")
    suspend fun selectShoppingListsInEditMode(): ShoppingListEntity?

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    suspend fun selectShoppingListWithItems(listId: Long?): ShoppingListCrossRef?

    @Query("SELECT * FROM shopping_list")
    suspend fun selectAllListsWithItems(): List<ShoppingListCrossRef>

    // --- Delete ---
    @Query("UPDATE shopping_list SET deleted = 1, lastUpdated = :updatedAt WHERE id = :listId")
    suspend fun deleteList(listId: Long, updatedAt: Long = System.currentTimeMillis())

    @Query("DELETE FROM shopping_list WHERE deleted = 1")
    suspend fun clearDeletedLists(): Int

    @Query("DELETE FROM shopping_list")
    suspend fun deleteAll()

    // --- Transactions ---

    @Transaction
    suspend fun openListInEditMode(): Long? {
        return selectShoppingListsInEditMode()?.id ?: insertShoppingList(ShoppingListEntity(inEditing = true))
    }

    @Transaction
    suspend fun cloneShoppingList(listId: Long) {
        val originalData = selectShoppingListWithItems(listId) ?: return
        val newList = originalData.shoppingList.copy(
            id = null,
            title = "Cópia de ${originalData.shoppingList.title.orEmpty()}",
            lastUpdated = System.currentTimeMillis(),
            inEditing = false,
            uuid = null
        )
        val newListId = insertShoppingList(newList)
        val clonedItems = originalData.items.map { item ->
            item.copy(
                id = null,
                shoppingListId = newListId,
                lastUpdated = System.currentTimeMillis(),
                uuid = null
            )
        }
        if (clonedItems.isNotEmpty()) {
            insertItems(clonedItems)
        }
    }
}