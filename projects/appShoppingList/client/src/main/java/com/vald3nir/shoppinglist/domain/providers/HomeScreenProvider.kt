package com.vald3nir.shoppinglist.domain.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListDTO

class HomeScreenProvider : PreviewParameterProvider<List<ShoppingListDTO>> {
    override val values: Sequence<List<ShoppingListDTO>> = sequenceOf(MockShoppingListDTO.lists())
}

private object MockShoppingListDTO {

    val items: List<ItemShoppingListDTO> = listOf(
        ItemShoppingListDTO(id = 0, product = "Arroz", category = "Alimentos", unitPrice = 5.8, quantity = 2, isAdd = true),
        ItemShoppingListDTO(id = 1, product = "Feijão", category = "Alimentos", unitPrice = 4.0, quantity = 0, isAdd = false),
        ItemShoppingListDTO(id = 2, product = "Refrigerante", category = "Bebidas", unitPrice = 10.0, quantity = 3, isAdd = true),
        ItemShoppingListDTO(id = 3, product = "Suco", category = "Bebidas", unitPrice = 4.8, quantity = 5, isAdd = false),
        ItemShoppingListDTO(id = 4, product = "Sal", category = "Alimentos", unitPrice = 0.90, quantity = 1, isAdd = false),
        ItemShoppingListDTO(id = 5, product = "Café", category = "Bebidas", unitPrice = 15.0, quantity = 4, isAdd = true),
        ItemShoppingListDTO(id = 6, product = "Leite", category = "Alimentos", unitPrice = 5.99, quantity = 2, isAdd = false),
        ItemShoppingListDTO(id = 7, product = "Queijo", category = "Alimentos", unitPrice = 10.0, quantity = 3, isAdd = false),
        ItemShoppingListDTO(id = 8, product = "Presunto", category = "Alimentos", unitPrice = 6.0, quantity = 1, isAdd = false),
        ItemShoppingListDTO(id = 9, product = "Frango", category = "Alimentos", unitPrice = 12.0, quantity = 1, isAdd = false),
    )

    fun lists() = listOf(
        ShoppingListDTO(id = 0, title = "Mercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 1, title = "Supermercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 2, title = "Pão de Açúcar", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 3, title = "Padaria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 4, title = "Farmácia", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 5, title = "Livraria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 6, title = "Mercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 7, title = "Supermercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 8, title = "Pão de Açúcar", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 9, title = "Padaria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 10, title = "Farmácia", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 11, title = "Livraria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 12, title = "Livraria 2", date = "27/02/2025", items = items),
    )
}