package com.vald3nir.shoppinglist.core.repository.sync.di

import com.vald3nir.shoppinglist.core.repository.sync.SyncListsRepository
import com.vald3nir.shoppinglist.core.repository.sync.SyncListsRepositoryImpl
import com.vald3nir.shoppinglist.core.repository.sync.SyncProductsRepository
import com.vald3nir.shoppinglist.core.repository.sync.SyncProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SyncModule {

    @Binds
    @Singleton
    abstract fun bindSyncProductsRepository(impl: SyncProductsRepositoryImpl): SyncProductsRepository

    @Binds
    @Singleton
    abstract fun bindSyncListsRepository(impl: SyncListsRepositoryImpl): SyncListsRepository
}