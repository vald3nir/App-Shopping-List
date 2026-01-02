package com.vald3nir.shoppinglist.domain

import com.vald3nir.shoppinglist.core.domain.dto.CategoryDTO
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO

data class ItemListDetailDTO(
    val categories: List<CategoryDTO> = emptyList(),
    val productNames: List<String> = emptyList(),
    val item: ItemShoppingListDTO = ItemShoppingListDTO(),
)