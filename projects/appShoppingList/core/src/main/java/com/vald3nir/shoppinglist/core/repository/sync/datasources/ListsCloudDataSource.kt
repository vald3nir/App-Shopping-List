package com.vald3nir.shoppinglist.core.repository.sync.datasources

import com.vald3nir.shoppinglist.core.repository.sync.model.ItemListSyncModel
import com.vald3nir.shoppinglist.core.repository.sync.model.ListSyncModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

interface ListsCloudDataSource {
    suspend fun addLists(lists: List<ListSyncModel>)
    suspend fun addItemsList(items: List<ItemListSyncModel>)
    suspend fun getLists(owner: String): List<ListSyncModel>
    suspend fun getItems(owner: String): List<ItemListSyncModel>
    suspend fun deleteAllLists(owner: String)
    suspend fun deleteAllLists(owner: String, listIds: List<Long>)
    suspend fun deleteAllItems(owner: String)
    suspend fun deleteAllItems(owner: String, listIds: List<Long>)
}

internal class ListsCloudDataSourceImpl @Inject constructor(private val supabaseClient: SupabaseClient) : ListsCloudDataSource {

    override suspend fun addLists(lists: List<ListSyncModel>) {
        if (lists.isEmpty()) return
        supabaseClient.from("shopping_list").insert(lists)
    }

    override suspend fun addItemsList(items: List<ItemListSyncModel>) {
        if (items.isEmpty()) return
        supabaseClient.from("item_list").insert(items)
    }

    override suspend fun getLists(owner: String): List<ListSyncModel> {
        val json = supabaseClient
            .from("shopping_list")
            .select {
                filter { eq("owner", owner) }
            }
        return json.decodeList()
    }

    override suspend fun getItems(owner: String): List<ItemListSyncModel> {
        val json = supabaseClient
            .from("item_list")
            .select {
                filter { eq("owner", owner) }
            }
        return json.decodeList()
    }

    override suspend fun deleteAllLists(owner: String) {
        supabaseClient
            .from("shopping_list")
            .delete {
                filter { eq("owner", owner) }
            }
    }

    override suspend fun deleteAllLists(owner: String, listIds: List<Long>) {
        supabaseClient
            .from("shopping_list")
            .delete {
                filter {
                    eq("owner", owner)
                    isIn("list_id", listIds)
                }
            }
    }

    override suspend fun deleteAllItems(owner: String) {
        supabaseClient
            .from("item_list")
            .delete {
                filter { eq("owner", owner) }
            }
    }

    override suspend fun deleteAllItems(owner: String, listIds: List<Long>) {
        supabaseClient
            .from("item_list")
            .delete {
                filter {
                    eq("owner", owner)
                    isIn("list_id", listIds)
                }

            }
    }
}