package com.vald3nir.shoppinglist.presentation.features.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.presentation.components.buildTopBarWithAvatar
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFloatingButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.templates.ToolkitBaseContent

@Composable
internal fun HomeScreenContent(
    userImageUrl: String? = null,
    searchQuery: String = "",
    lists: List<ShoppingListDTO>,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    filterLists: (key: String) -> Unit = {},
    onAvatarClick: () -> Unit = {},
    onClickAddData: () -> Unit = {},
    onClickShowDetail: (shoppingListID: Long?) -> Unit = {}
) {
    ToolkitBaseContent(
        snackBarHostState = snackBarHostState,
        topBar = buildTopBarWithAvatar(
            title = stringResource(R.string.home_title),
            userImageUrl = userImageUrl,
            onAvatarClick = onAvatarClick,
        ),
        floatingActionButton = {
            ToolkitFloatingButton(
                imageVector = ToolkitIconCatalog.Add,
                onClick = onClickAddData
            )
        },
    ) {
        ToolkitSearchFiled(
            label = stringResource(R.string.home_search_list),
            searchQuery = searchQuery,
            onValueChange = filterLists,
        )
        DefaultSpaceHeight()
        LazyColumn {
            itemsIndexed(lists) { _, item ->
                HomeListsSection(
                    title = item.title.orEmpty(),
                    date = item.date.orEmpty(),
                    onClick = { onClickShowDetail(item.id) }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewContent(
    @PreviewParameter(HomeScreenProvider::class)
    lists: List<ShoppingListDTO>,
) {
    AppTheme {
        ToolkitBackground {
            HomeScreenContent(lists = lists)
        }
    }
}

private class HomeScreenProvider : PreviewParameterProvider<List<ShoppingListDTO>> {
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