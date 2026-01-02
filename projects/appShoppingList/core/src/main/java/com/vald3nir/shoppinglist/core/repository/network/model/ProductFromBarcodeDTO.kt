package com.vald3nir.shoppinglist.core.repository.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductFromBarcodeDTO(
    val code: String?,
    val product: ProductFromBarcodeInfoDTO?,
)

@Serializable
data class ProductFromBarcodeInfoDTO(
    @SerializedName("product_name")
    val productName: String?
)