package com.vald3nir.shoppinglist.repository.usecases

import android.content.Context
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.db.mock.MockShoppingListModel
import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItems
import com.vald3nir.shoppinglist.domain.dto.ProductDTO

fun Context.importProductsFromDataset(): List<ProductDTO> {
    val products = mutableListOf<ProductDTO>()
    runCatching {
        assets.open("products.csv").bufferedReader().useLines { lines ->
            lines.drop(1).forEach { line -> // Ignore the header line
                val fields = line.split(",")
                if (fields.size >= 2) {
                    products.add(ProductDTO(name = fields[0].trim(), category = fields[1].trim()))
                }
            }
        }
    }
    return products
}

suspend fun ShoppingListDao.importShoppingListFromMock() {
    val shoppingLists: List<ShoppingListWithItems> = arrayListOf(
        ShoppingListWithItems(shoppingList = MockShoppingListModel.list1, items = MockShoppingListModel.items1),
        ShoppingListWithItems(shoppingList = MockShoppingListModel.list2, items = MockShoppingListModel.items2),
        ShoppingListWithItems(shoppingList = MockShoppingListModel.list3, items = MockShoppingListModel.items3),
    )
    this.cleanAndInsert(shoppingLists)
}