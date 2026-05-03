package com.vald3nir.shoppinglist.domain.dto.upload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemListSyncModel(
    val id: String?,
    @SerialName("item_id")
    val itemId: Long?,
    @SerialName("list_id")
    val listId: Long?,
    val category: String?,
    @SerialName("is_add")
    val isAdd: Boolean? = false,
    val product: String?,
    @SerialName("icon_url")
    val iconURL: String?,
    val quantity: Int?,
    @SerialName("unit_price")
    val unitPrice: Double?,
    val owner: String?,
    @SerialName("created_at")
    val createdAt: String?,
)