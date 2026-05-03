package com.vald3nir.shoppinglist.domain.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.domain.dto.CreateItemListContentDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO

internal class CreateItemListProvider : PreviewParameterProvider<CreateItemListContentDTO> {
    override val values: Sequence<CreateItemListContentDTO> = sequenceOf(
        CreateItemListContentDTO(
            topItems = listOf(
                ItemShoppingListDTO(id = "0", product = "Arroz", unitPrice = 5.8, quantity = 2, onCart = true),
                ItemShoppingListDTO(id = "1", product = "Feijão", unitPrice = 4.0, quantity = 10, onCart = false),
                ItemShoppingListDTO(id = "2", product = "Refrigerante", unitPrice = 10.0, quantity = 3, onCart = true),
                ItemShoppingListDTO(id = "3", product = "Suco", unitPrice = 4.8, quantity = 5, onCart = false),
                ItemShoppingListDTO(id = "4", product = "Sal", unitPrice = 0.90, quantity = 1, onCart = false),
                ItemShoppingListDTO(id = "5", product = "Café", unitPrice = 15.0, quantity = 4, onCart = true),
            ),
            productNames = listOf("Arroz", "Feijão", "Refrigerante", "Suco", "Sal", "Café")
        )
    )
}