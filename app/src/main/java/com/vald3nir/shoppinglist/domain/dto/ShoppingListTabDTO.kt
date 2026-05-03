package com.vald3nir.shoppinglist.domain.dto

import androidx.compose.ui.graphics.vector.ImageVector

data class ShoppingListTabDTO(
    val title: String,
    val icon: ImageVector,
    val action: () -> Unit,
)