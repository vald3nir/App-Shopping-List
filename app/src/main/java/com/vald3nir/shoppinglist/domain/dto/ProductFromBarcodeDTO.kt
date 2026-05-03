package com.vald3nir.shoppinglist.domain.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
internal data class ProductFromBarcodeDTO(
    @SerializedName("status") val status: Int, // 1 significa encontrado, 0 não encontrado
    @SerializedName("product") val product: ProductData? = null
) {
    fun isValid(): Boolean = status == 1

    override fun toString(): String {
        return Json.encodeToString(this)
    }
}

@Serializable
internal data class ProductData(
    @SerializedName("product_name") val productName: String? = null,
    @SerializedName("brands") val brands: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null
)