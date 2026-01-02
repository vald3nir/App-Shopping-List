package com.vald3nir.shoppinglist.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vald3nir.shoppinglist.core.repository.database.entities.SyncAdminEntity
import com.vald3nir.shoppinglist.core.repository.database.entities.SyncAdminUniqueKey

@Dao
internal interface SyncAdminDao {

    // --- Create / Insert ---

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(admin: SyncAdminEntity)

    // --- Read / Queries ---

    @Query("SELECT * FROM db_admin WHERE id = $SyncAdminUniqueKey")
    fun getSyncAdmin(): SyncAdminEntity?

}