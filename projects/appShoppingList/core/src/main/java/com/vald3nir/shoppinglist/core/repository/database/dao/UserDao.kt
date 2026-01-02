package com.vald3nir.shoppinglist.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.core.repository.database.entities.UserEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.UserUniqueKey
import kotlinx.coroutines.flow.Flow

@Dao
internal interface UserDao {

    // --- Create / Insert ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: UserEntity)

    // --- Read / Queries ---

    @Query("SELECT * FROM user WHERE id = $UserUniqueKey")
    fun loadCurrentUserFlow(): Flow<UserEntity?>

    @Query("SELECT * FROM user WHERE id = $UserUniqueKey")
    suspend fun loadCurrentUser(): UserEntity?

    // --- Delete ---

    @Query("DELETE FROM user")
    suspend fun clearUser()

    // --- Transactions ---

    @Transaction
    suspend fun getCurrentUser(): UserEntity {
        return loadCurrentUser() ?: UserEntity(id = UserUniqueKey)
    }
}