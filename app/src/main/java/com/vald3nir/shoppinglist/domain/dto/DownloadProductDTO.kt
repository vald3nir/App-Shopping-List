package com.vald3nir.shoppinglist.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DownloadProductDTO(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?,
)