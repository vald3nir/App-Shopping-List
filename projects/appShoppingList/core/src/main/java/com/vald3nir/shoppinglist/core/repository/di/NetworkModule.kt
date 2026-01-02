package com.vald3nir.shoppinglist.core.repository.di

import com.vald3nir.shoppinglist.core.BuildConfig
import com.vald3nir.shoppinglist.core.repository.network.api.PublicServicesAPI
import com.vald3nir.toolkit.core.services.rest.NetworkingSetup
import com.vald3nir.toolkit.core.services.supabase.setupSupabaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun bindsPublicServicesAPI() = NetworkingSetup.provideApiService<PublicServicesAPI>(baseURL = "https://world.openfoodfacts.org/")

    @Provides
    @Singleton
    fun provideSupabaseClient() = setupSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY,
    )
}