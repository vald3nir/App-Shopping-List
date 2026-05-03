package com.vald3nir.shoppinglist.domain.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO

internal class ItemsShoppingListProvider : PreviewParameterProvider<List<ItemShoppingListDTO>> {
    override val values: Sequence<List<ItemShoppingListDTO>> = sequenceOf(
        listOf(
            ItemShoppingListDTO(
                product = "Arroz",
                quantity = 10,
                unitPrice = 5.0,
                onCart = true,
            ),
            ItemShoppingListDTO(
                product = "Açúcar",
                quantity = 5,
                unitPrice = 0.0,
                onCart = false,
            ),
            ItemShoppingListDTO(
                product = "Café",
                quantity = 1,
                unitPrice = 0.6,
                onCart = true,
            ),
        )
    )
}