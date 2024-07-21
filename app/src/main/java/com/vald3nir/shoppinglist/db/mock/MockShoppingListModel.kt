package com.vald3nir.shoppinglist.db.mock

import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.toolkit.helpers.utils.getCurrentDate

object MockShoppingListModel {

    val list1 = ShoppingListModal(title = "Supermercado", date = getCurrentDate())
    val list2 = ShoppingListModal(title = "Frigorífico", date = getCurrentDate())
    val list3 = ShoppingListModal(title = "Feira", date = getCurrentDate())

    val items1: List<ItemShoppingListModal> = listOf(
        ItemShoppingListModal(title = "Feijão", category = "Mercearia", unitPrice = 5.0, quantity = 1, isAdd = false),
        ItemShoppingListModal(title = "Óleo", category = "Mercearia", unitPrice = 10.0, quantity = 1, isAdd = false),
        ItemShoppingListModal(title = "Açúcar", category = "Mercearia", unitPrice = 3.0, quantity = 4, isAdd = false),
        ItemShoppingListModal(title = "Maminha", category = "Açougue e Peixaria", unitPrice = 0.90, quantity = 1, isAdd = false),
        ItemShoppingListModal(title = "Patinho", category = "Açougue e Peixaria", unitPrice = 0.90, quantity = 1, isAdd = false),
        ItemShoppingListModal(title = "Café", category = "Bebidas", unitPrice = 15.0, quantity = 5, isAdd = true),
        ItemShoppingListModal(title = "Refrigerante", category = "Bebidas", unitPrice = 15.0, quantity = 5, isAdd = true),
        ItemShoppingListModal(title = "Pilha AAA", category = "Eletrônicos", unitPrice = 3.5, quantity = 2, isAdd = true),
    )
    val items2: List<ItemShoppingListModal> = listOf(
        ItemShoppingListModal(title = "Picanha", category = "Açougue e Peixaria", unitPrice = 100.0, quantity = 2, isAdd = true),
        ItemShoppingListModal(title = "Frango", category = "Açougue e Peixaria", unitPrice = 20.0, quantity = 1, isAdd = false),
        ItemShoppingListModal(title = "Linguiça", category = "Açougue e Peixaria", unitPrice = 15.0, quantity = 1, isAdd = false),
        ItemShoppingListModal(title = "Carne Moida", category = "Açougue e Peixaria", unitPrice = 25.0, quantity = 4, isAdd = false)
    )
    val items3: List<ItemShoppingListModal> = listOf(
        ItemShoppingListModal(title = "Banana", category = "Hortifruti", unitPrice = 2.0, quantity = 6, isAdd = false),
        ItemShoppingListModal(title = "Maçã", category = "Hortifruti", unitPrice = 2.5, quantity = 3, isAdd = false),
        ItemShoppingListModal(title = "Laranja", category = "Hortifruti", unitPrice = 3.0, quantity = 2, isAdd = true)
    )
}