package com.vald3nir.shoppinglist.repository.di

import android.content.Context
import androidx.room.Room
import com.vald3nir.shoppinglist.repository.database.AppDatabase
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
    fun bindsAppDatabase(@ApplicationContext appContext: Context): AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "database.db")
        .fallbackToDestructiveMigration(true)
        .build()

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
}