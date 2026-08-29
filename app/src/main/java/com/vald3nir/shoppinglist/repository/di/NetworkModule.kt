package com.vald3nir.shoppinglist.repository.di

import android.content.Context
import com.vald3nir.shoppinglist.repository.api.SearchProductAPI
import com.vald3nir.toolkit.core.services.rest.NetworkingSetup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun bindsPublicServicesAPI(@ApplicationContext context: Context) = NetworkingSetup.provideApiService<SearchProductAPI>(
        context = context,
        baseURL = "https://world.openfoodfacts.org/"
    )
}