package com.vald3nir.shoppinglist.core.repository.sync.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategorySyncModel(
    val id: String?,
    val name: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("icon_url")
    val iconURL: String?,
)