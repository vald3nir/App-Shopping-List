package com.vald3nir.shoppinglist.core.repository.database.di

import android.content.Context
import androidx.room.Room
import com.vald3nir.shoppinglist.core.repository.UserPreferencesDataSource
import com.vald3nir.shoppinglist.core.repository.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun bindsUserPreferencesDataSource(@ApplicationContext context: Context) = UserPreferencesDataSource(context)

    @Provides
    @Singleton
    fun bindsAppDatabase(@ApplicationContext appContext: Context): AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "database.db")
        .fallbackToDestructiveMigration(true)
        .build()

    @Provides
    @Singleton
    fun bindsSyncAdminDao(database: AppDatabase) = database.getSyncAdminDao()

    @Provides
    @Singleton
    fun bindsUserDao(database: AppDatabase) = database.getUserDao()

    @Provides
    @Singleton
    fun bindsShoppingListDao(database: AppDatabase) = database.getShoppingListDao()

    @Provides
    @Singleton
    fun bindsItemShoppingListDao(database: AppDatabase) = database.getItemShoppingListDao()

    @Provides
    @Singleton
    fun bindsProductsDao(database: AppDatabase) = database.getProductsDao()

    @Provides
    @Singleton
    fun bindsCategoryDao(database: AppDatabase) = database.getCategoryDao()
}