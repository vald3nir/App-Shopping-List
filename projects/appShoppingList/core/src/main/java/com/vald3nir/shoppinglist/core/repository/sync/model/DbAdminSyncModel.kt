package com.vald3nir.shoppinglist.core.repository.sync.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DbAdminSyncModel (
    val id: String?,
    @SerialName("products_version")
    val productsVersion: Long?,
    @SerialName("categories_version")
    val categoriesVersion: Long?,
    @SerialName("last_updated")
    val lastUpdated: String?,
)