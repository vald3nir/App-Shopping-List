package com.vald3nir.shoppinglist.core.repository.di

import com.vald3nir.shoppinglist.core.repository.AuthenticatedUserRepositoryImpl
import com.vald3nir.shoppinglist.core.repository.CategoryRepository
import com.vald3nir.shoppinglist.core.repository.CategoryRepositoryImpl
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ItemShoppingListRepositoryImpl
import com.vald3nir.shoppinglist.core.repository.ProductsRepository
import com.vald3nir.shoppinglist.core.repository.ProductsRepositoryImpl
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.core.repository.ShoppingListRepositoryImpl
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {



    @Binds
    @Singleton
    abstract fun bindShoppingListRepository(impl: ShoppingListRepositoryImpl): ShoppingListRepository

    @Binds
    @Singleton
    abstract fun bindItemShoppingListRepository(impl: ItemShoppingListRepositoryImpl): ItemShoppingListRepository

    @Binds
    @Singleton
    abstract fun bindProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindAuthenticatedUserRepository(impl: AuthenticatedUserRepositoryImpl): AuthenticatedUserRepository

}