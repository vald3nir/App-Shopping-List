package com.vald3nir.shoppinglist.core.repository.sync.datasources

import com.vald3nir.shoppinglist.core.repository.sync.model.CategorySyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.DbAdminSyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.ProductSyncModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

interface ProductsCloudDataSource {
    suspend fun getDbAdmin(): DbAdminSyncModel
    suspend fun getProducts(): List<ProductSyncModel>
    suspend fun getCategories(): List<CategorySyncModel>
}

internal class ProductsCloudDataSourceImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProductsCloudDataSource {

    override suspend fun getDbAdmin(): DbAdminSyncModel {
        return supabaseClient
            .from("db_admin")
            .select()
            .decodeSingle()
    }

    override suspend fun getProducts(): List<ProductSyncModel> {
        return supabaseClient
            .from("product")
            .select()
            .decodeList()
    }

    override suspend fun getCategories(): List<CategorySyncModel> {
        return supabaseClient
            .from("category")
            .select()
            .decodeList()
    }
}
