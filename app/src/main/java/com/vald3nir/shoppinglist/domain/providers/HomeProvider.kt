package com.vald3nir.shoppinglist.domain.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO

internal class HomeProvider : PreviewParameterProvider<List<ShoppingListDTO>> {
    override val values: Sequence<List<ShoppingListDTO>> = sequenceOf(MockShoppingLists.lists())
}

private object MockShoppingLists {

    fun lists() = listOf(
        ShoppingListDTO(id = "0f4ef34e-c6d6-4da4-95cd-fce640424f2e", title = "Mercado", createdAt = "27/02/2025", items = items1),
        ShoppingListDTO(id = "ec4d8937-f0d0-4587-a43e-ed7116f5c5db", title = "Supermercado", createdAt = "27/02/2025", items = items2),
        ShoppingListDTO(id = "f87d96db-5961-4e7c-b5c4-a623f6f9bc3d", title = "Pão de Açúcar", createdAt = "27/02/2025", items = items3),
    )

    val items1: List<ItemShoppingListDTO> = listOf(
        ItemShoppingListDTO(id = "a3941379-e4e9-4744-a54b-cd9c16e79a10", product = "Arroz", shoppingListId = "0f4ef34e-c6d6-4da4-95cd-fce640424f2e", unitPrice = 5.8, quantity = 2, onCart = true),
        ItemShoppingListDTO(id = "c2382f7d-d188-4e74-bbee-3f9d15f57825", product = "Feijão", shoppingListId = "0f4ef34e-c6d6-4da4-95cd-fce640424f2e", unitPrice = 4.0, quantity = 0, onCart = false),
        ItemShoppingListDTO(id = "136e3b74-59ca-4688-badb-28b785c66b56", product = "Refrigerante", shoppingListId = "0f4ef34e-c6d6-4da4-95cd-fce640424f2e", unitPrice = 10.0, quantity = 3, onCart = true),
        ItemShoppingListDTO(id = "d2f2f78f-fb4c-4f48-95ca-3b5ed3e9e997", product = "Suco", shoppingListId = "0f4ef34e-c6d6-4da4-95cd-fce640424f2e", unitPrice = 4.8, quantity = 5, onCart = false),
        ItemShoppingListDTO(id = "fa8f0f07-23e9-4dc6-82d3-e44e8b30a743", product = "Sal", shoppingListId = "0f4ef34e-c6d6-4da4-95cd-fce640424f2e", unitPrice = 0.90, quantity = 1, onCart = false),
    )
    val items2: List<ItemShoppingListDTO> = listOf(
        ItemShoppingListDTO(id = "b7be438c-84cb-4912-b1aa-2439cfde8e89", product = "Queijo", shoppingListId = "ec4d8937-f0d0-4587-a43e-ed7116f5c5db", quantity = 3, onCart = false),
        ItemShoppingListDTO(id = "f7ce38de-eb44-4abf-9d30-e15ec0e6b9b4", product = "Presunto", shoppingListId = "ec4d8937-f0d0-4587-a43e-ed7116f5c5db", quantity = 1, onCart = false),
        ItemShoppingListDTO(id = "4d07167e-59f8-4d34-85d7-2ddbd3af95d6", product = "Frango", shoppingListId = "ec4d8937-f0d0-4587-a43e-ed7116f5c5db", quantity = 1, onCart = false),
    )
    val items3: List<ItemShoppingListDTO> = listOf(
        ItemShoppingListDTO(id = "37f5978d-5a54-4fd9-a0b8-dfa0de3a95d6", product = "Arroz", shoppingListId = "f87d96db-5961-4e7c-b5c4-a623f6f9bc3d", unitPrice = 5.8, quantity = 2, onCart = true),
        ItemShoppingListDTO(id = "7159b0d3-f04b-47f6-8687-1af2ae94f5a5", product = "Feijão", shoppingListId = "f87d96db-5961-4e7c-b5c4-a623f6f9bc3d", unitPrice = 4.0, quantity = 0, onCart = false),
    )
}