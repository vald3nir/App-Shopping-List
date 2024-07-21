package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.usecases.splitItemsByCategory
import com.vald3nir.shoppinglist.domain.usecases.splitItemsByStatus
import com.vald3nir.shoppinglist.presentation.components.ComponentSection
import com.vald3nir.toolkit.compose.components.base.HalfSpaceHeight
import com.vald3nir.toolkit.compose.components.base.halfSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ComponentItemsListDetail(
    shoppingList: ShoppingListDTO?,
    colors: ScreenColorSchema,
    onClickSeeDetails: (ItemShoppingListDTO) -> Unit = {},
    onUpdateItem: (ItemShoppingListDTO) -> Unit = {},
) {
    if (shoppingList == null) return
    val (itemsAdded, itemsNotAdded) = shoppingList.splitItemsByStatus()
    val categories = itemsNotAdded.splitItemsByCategory()

    Column(modifier = Modifier.background(colors.backgroundColor)) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(halfSpace)
        ) {

            categories.forEach { category ->
                if (category.value.isNotEmpty()) {
                    item {
                        Column {
                            ComponentSection(
                                text = category.key.ifEmpty { stringResource(R.string.several) },
                                background = Color.Blue,
                            )
                        }
                    }
                    itemsIndexed(items = category.value, itemContent = { _, item ->
                        ComponentItemShoppingCart(
                            item = item,
                            colors = colors,
                            onClickSeeDetails = onClickSeeDetails,
                            onUpdateItem = onUpdateItem,
                        )
                        HalfSpaceHeight()
                    })
                }
            }

            if (itemsAdded.isNotEmpty()) {
                item {
                    ComponentSection(
                        text = stringResource(R.string.items_added),
                        background = Color.Red,
                    )
                }
                itemsIndexed(items = itemsAdded, itemContent = { _, item ->
                    ComponentItemShoppingCart(
                        item = item,
                        colors = colors,
                        onClickSeeDetails = onClickSeeDetails,
                        onUpdateItem = onUpdateItem,
                    )
                    HalfSpaceHeight()
                })
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentItemsListDetail(
        colors = DefaultThemeColors().lightColors,
        shoppingList = MockShoppingListDTO.lists().first(),
    )
}