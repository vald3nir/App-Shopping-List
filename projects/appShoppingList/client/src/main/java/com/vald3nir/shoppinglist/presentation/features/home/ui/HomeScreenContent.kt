package com.vald3nir.shoppinglist.presentation.features.home.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.shoppinglist.domain.providers.HomeScreenProvider
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBarWithAvatar
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun HomeScreenContent(
    userImageUrl: String? = null,
    searchQuery: String = "",
    lists: List<ShoppingListDTO>,
    filterLists: (key: String) -> Unit = {},
    onAvatarClick: () -> Unit = {},
    onClickAddData: () -> Unit = {},
    onClickShowDetail: (shoppingListID: Long?) -> Unit = {}
) {
    ToolkitColumn {
        ToolkitTopBarWithAvatar(
            title = stringResource(R.string.home_title),
            textCenterAligned = true,
            userImageUrl = userImageUrl,
            onAvatarClick = onAvatarClick
        )
        ToolkitSearchFiled(
            label = stringResource(R.string.home_search_list),
            searchQuery = searchQuery,
            onValueChange = filterLists,
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(lists) { _, item ->
                HomeListsSection(
                    title = item.title.orEmpty(),
                    date = item.date.orEmpty(),
                    onClick = { onClickShowDetail(item.id) }
                )
            }
        }
        ToolkitBaseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ToolkitSpacingMd),
            text = stringResource(R.string.new_list),
            leadingIcon = ToolkitIconCatalog.Add,
            onClick = onClickAddData
        )
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