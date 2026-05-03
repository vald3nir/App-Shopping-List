package com.vald3nir.shoppinglist.presentation.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.providers.HomeProvider
import com.vald3nir.shoppinglist.presentation.components.AppPreview
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.formatCurrency
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitCard
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBarWithAvatar
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    redirectToCreateList: () -> Unit,
    redirectToListDetail: (shoppingListID: String?) -> Unit,
    redirectToProfile: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val homeData by viewModel.homeDataFlow.collectAsStateWithLifecycle()

    when (uiState) {
        is BaseUiState.LoadingState -> {
            ToolkitLoadingFullscreen()
            return
        }

        is BaseUiState.EmptySate -> {
            EmptyState(
                userImageUrl = homeData?.user?.photoUrl,
                onClickAddData = redirectToCreateList,
                onAvatarClick = redirectToProfile,
            )
            return
        }

        is BaseUiState.ShowState -> {
            ScreenContent(
                searchQuery = searchQuery,
                lists = homeData?.lists.orEmpty(),
                userImageUrl = homeData?.user?.photoUrl,
                onAvatarClick = redirectToProfile,
                onClickAddData = redirectToCreateList,
                filterLists = { viewModel.onSearchQueryChanged(it) },
                onClickShowDetail = redirectToListDetail
            )
        }

        else -> Unit
    }
}

@Composable
private fun ScreenContent(
    userImageUrl: String? = null,
    searchQuery: String = "",
    lists: List<ShoppingListDTO>,
    filterLists: (key: String) -> Unit = {},
    onAvatarClick: () -> Unit = {},
    onClickAddData: () -> Unit = {},
    onClickShowDetail: (shoppingListID: String?) -> Unit = {}
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
                HomeListsSection(item, onClickShowDetail)
            }
        }
        ToolkitBaseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ToolkitSpacingMd),
            text = stringResource(R.string.create_list_btn_add),
            leadingIcon = ToolkitIconCatalog.Add,
            onClick = onClickAddData
        )
    }
}

@Composable
internal fun HomeListsSection(list: ShoppingListDTO, onClickShowDetail: (listId: String?) -> Unit = {}) {
    ToolkitCard(
        modifier = Modifier.padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingSm),
        onClick = { onClickShowDetail(list.id) },
        content = {
            Row(
                modifier = Modifier
                    .padding(ToolkitSpacingMd)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ToolkitText(text = list.title.orEmpty(), style = ToolkitTextStyle.TitleSmall)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = ToolkitSpacingSm),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ToolkitText(
                            text = list.dateLabel,
                            style = ToolkitTextStyle.BodySmall
                        )
                        if (list.price > 0) {
                            ToolkitText(
                                modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
                                text = list.price.formatCurrency(),
                                style = ToolkitTextStyle.BodySmall
                            )
                        }
                    }
                }
                ToolkitIcon(imageVector = ToolkitIconCatalog.ArrowIndicatorRight)
            }
        }
    )
}

@Composable
private fun EmptyState(
    userImageUrl: String? = null,
    onAvatarClick: () -> Unit = {},
    onClickAddData: () -> Unit = {}
) {
    ToolkitColumn {
        ToolkitTopBarWithAvatar(
            title = stringResource(R.string.home_title),
            textCenterAligned = true,
            userImageUrl = userImageUrl,
            onAvatarClick = onAvatarClick
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            ToolkitEmptyStateScreen(
                title = stringResource(R.string.no_registered_lists),
                btnText = stringResource(R.string.insert_item_btn_add),
                imageVector = ToolkitIconCatalog.Inbox,
                onClickBtn = onClickAddData,
            )
        }
    }
}

@AppPreview
@Composable
private fun PreviewEmptyState() {
    ToolkitPreviewContainer {
        EmptyState()
    }
}


@AppPreview
@Composable
private fun PreviewContent(
    @PreviewParameter(HomeProvider::class)
    lists: List<ShoppingListDTO>,
) {
    ToolkitPreviewContainer {
        ScreenContent(lists = lists)
    }
}