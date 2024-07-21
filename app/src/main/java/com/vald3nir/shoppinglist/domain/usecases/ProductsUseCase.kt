package com.vald3nir.shoppinglist.domain.usecases

import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.toolkit.compose.components.inputs.ToolkitAutoCompleteInputData

fun List<ProductDTO>.findProductByName(name: String): ProductDTO {
    val formattedName = name.lowercase().trim()
    return firstOrNull { it.name?.lowercase()?.trim() == formattedName } ?: ProductDTO().copy(name = name)
}

fun List<ProductDTO>.formatProducts() = map { ToolkitAutoCompleteInputData(id = it.id, text = it.name.orEmpty()) }