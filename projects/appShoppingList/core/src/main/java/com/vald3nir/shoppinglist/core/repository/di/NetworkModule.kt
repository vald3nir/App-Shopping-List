package com.vald3nir.shoppinglist.core.repository.di

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
        // todo valdenir mover para .env
        supabaseUrl = "https://krkzqpmicpyqaupfjkvr.supabase.co",
        supabaseKey = "sb_publishable_z-86pmTPjzgr3bhWxCB8Wg_GuayE91e"
    )
}