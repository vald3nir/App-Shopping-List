package com.vald3nir.shoppinglist.domain.dto.upload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListSyncModel(
    val id: String?,
    @SerialName("created_at")
    val createdAt: String?,
    val owner: String?,
    @SerialName("list_id")
    val listId: Long?,
    val title: String?,
)