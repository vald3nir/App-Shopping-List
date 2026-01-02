package com.vald3nir.shoppinglist.domain.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO

class ItemsShoppingListProvider : PreviewParameterProvider<List<ItemShoppingListDTO>> {
    override val values: Sequence<List<ItemShoppingListDTO>> = sequenceOf(
        listOf(
            ItemShoppingListDTO(
                category = "Mercearia",
                product = "Arroz",
                quantity = 10,
                unitPrice = 5.0,
                isAdd = true,
            ),
            ItemShoppingListDTO(
                category = "Mercearia",
                product = "Açúcar",
                quantity = 5,
                unitPrice = 2.5,
                isAdd = false,
            ),
            ItemShoppingListDTO(
                category = "Mercearia",
                product = "Café",
                quantity = 1,
                unitPrice = 0.6,
                isAdd = true,
            ),
        )
    )
}