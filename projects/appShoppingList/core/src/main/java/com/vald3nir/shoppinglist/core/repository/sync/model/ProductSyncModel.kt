package com.vald3nir.shoppinglist.core.repository.sync.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSyncModel(
    val id: String?,
    val name: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("category_id")
    val categoryId: String?,
    @SerialName("brand_id")
    val brandId: String?,
)