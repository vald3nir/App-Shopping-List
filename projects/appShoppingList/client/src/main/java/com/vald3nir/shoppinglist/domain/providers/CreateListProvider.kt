package com.vald3nir.shoppinglist.domain.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.CreateListDTO

class CreateListProvider : PreviewParameterProvider<CreateListDTO> {
    override val values: Sequence<CreateListDTO> = sequenceOf(
        CreateListDTO(
            listId = 0,
            title = "Mercado",
            items = listOf(
                ItemShoppingListDTO(id = 0, product = "Arroz", category = "Alimentos", unitPrice = 5.8, quantity = 2, isAdd = true),
                ItemShoppingListDTO(id = 1, product = "Feijão", category = "Alimentos", unitPrice = 4.0, quantity = 10, isAdd = false),
                ItemShoppingListDTO(id = 2, product = "Refrigerante", category = "Bebidas", unitPrice = 10.0, quantity = 3, isAdd = true),
                ItemShoppingListDTO(id = 3, product = "Suco", category = "Bebidas", unitPrice = 4.8, quantity = 5, isAdd = false),
                ItemShoppingListDTO(id = 4, product = "Sal", category = "Alimentos", unitPrice = 0.90, quantity = 1, isAdd = false),
                ItemShoppingListDTO(id = 5, product = "Café", category = "Bebidas", unitPrice = 15.0, quantity = 4, isAdd = true),
                ItemShoppingListDTO(id = 6, product = "Arroz", category = "Alimentos", unitPrice = 5.8, quantity = 2, isAdd = true),
                ItemShoppingListDTO(id = 7, product = "Feijão", category = "Alimentos", unitPrice = 4.0, quantity = 10, isAdd = false),
                ItemShoppingListDTO(id = 8, product = "Refrigerante", category = "Bebidas", unitPrice = 10.0, quantity = 3, isAdd = true),
                ItemShoppingListDTO(id = 9, product = "Suco", category = "Bebidas", unitPrice = 4.8, quantity = 5, isAdd = false),
            )
        )
    )
}