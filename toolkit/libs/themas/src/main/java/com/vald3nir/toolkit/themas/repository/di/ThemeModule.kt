package com.vald3nir.toolkit.themas.repository.di

import android.content.Context
import com.vald3nir.toolkit.themas.repository.ThemaRepository
import com.vald3nir.toolkit.themas.repository.ThemaRepositoryImpl
import com.vald3nir.toolkit.themas.repository.datasource.ThemaDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ThemeRepository {
    @Binds
    internal abstract fun bindsThemaRepository(impl: ThemaRepositoryImpl): ThemaRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object ThemeDataSources {
    @Provides
    @Singleton
    fun bindsThemaDataSource(@ApplicationContext context: Context) = ThemaDataSource(context)
}