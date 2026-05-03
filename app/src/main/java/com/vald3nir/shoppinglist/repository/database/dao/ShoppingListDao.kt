package com.vald3nir.shoppinglist.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.shoppinglist.repository.database.entities.ShoppingListCrossRef
import com.vald3nir.shoppinglist.repository.database.entities.ShoppingListEntity
import com.vald3nir.toolkit.core.utils.security.generateUUID
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ShoppingListDao {

    // --- Create / Insert / Update ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(entity: ShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingLists(entities: List<ShoppingListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemShoppingListEntity>)

    @Query(
        """
        UPDATE shopping_list 
        SET title = :title,
            inEditing = 0
        WHERE id = :listId
    """
    )
    suspend fun closeEditionShoppingList(listId: String, title: String?)

    // --- Read / Queries ---

    // Busca por itens que começam com o mesmo título
    @Query("SELECT title FROM shopping_list WHERE markDeleted = 0 AND title LIKE :title || '%'")
    suspend fun getSimilarTitles(title: String): List<String>

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE markDeleted = 0 AND inEditing = 0 ORDER BY createdAt DESC")
    fun selectShoppingListsFlow(): Flow<List<ShoppingListCrossRef>>

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    fun getShoppingListFlow(listId: String): Flow<ShoppingListCrossRef?>

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    fun selectShoppingListWithItemsFlow(listId: String?): Flow<ShoppingListCrossRef>

    @Query("SELECT COUNT(*) = 0 FROM shopping_list")
    suspend fun isEmpty(): Boolean

    @Query("SELECT * FROM shopping_list WHERE inEditing = 1 LIMIT 1")
    suspend fun selectShoppingListsInEditMode(): ShoppingListEntity?

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    suspend fun selectShoppingListWithItems(listId: String?): ShoppingListCrossRef?

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE markDeleted = 0")
    suspend fun selectAllListsWithItems(): List<ShoppingListCrossRef>

    @Query("SELECT id FROM shopping_list WHERE markDeleted = 1")
    suspend fun selectMarkedDeletedListIds(): List<String>

    // --- Delete ---
    @Query("UPDATE shopping_list SET markDeleted = 1 WHERE id = :listId")
    suspend fun fakeDeleteList(listId: String)

    @Query("DELETE FROM shopping_list")
    suspend fun deleteAll()

    @Query("DELETE FROM shopping_list WHERE markDeleted = 1")
    suspend fun deleteAllFakes()

    // --- Transactions ---

    @Transaction
    suspend fun openListInEditMode(): String {
        var editingList = selectShoppingListsInEditMode()
        if (editingList == null) {
            editingList = ShoppingListEntity(inEditing = true)
            insertShoppingList(editingList)
        }
        return editingList.id
    }

    @Transaction
    suspend fun cloneShoppingList(listId: String) {
        val originalData = selectShoppingListWithItems(listId) ?: return
        val newListId = generateUUID()

        // Insert the new shopping list with a modified title
        insertShoppingList(
            ShoppingListEntity(
                id = newListId,
                title = "Cópia de ${originalData.shoppingList.title.orEmpty()}",
            )
        )
        // Insert new items with new IDs and the new shopping list ID
        val clonedItems = originalData.items.map { item ->
            item.copy(
                id = generateUUID(),
                shoppingListId = newListId
            )
        }
        if (clonedItems.isNotEmpty()) {
            insertItems(clonedItems)
        }
    }
}