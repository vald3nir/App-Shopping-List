package com.vald3nir.shoppinglist.presentation.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.features.home.ui.HomeEmptyState
import com.vald3nir.shoppinglist.presentation.features.home.ui.HomeScreenContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    redirectToCreateList: () -> Unit,
    redirectToListDetail: (shoppingListID: Long?) -> Unit,
    redirectToProfile: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val homeData by viewModel.homeDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.syncLists()
    }

    when (uiState) {
        is BaseUiState.LoadingState -> {
            ToolkitLoadingFullscreen()
            return
        }

        is BaseUiState.EmptySate -> {
            HomeEmptyState(
                userImageUrl = homeData?.user?.photoUrl,
                onClickAddData = redirectToCreateList,
                onAvatarClick = redirectToProfile,
            )
            return
        }

        is BaseUiState.ShowState -> {
            HomeScreenContent(
                searchQuery = searchQuery,
                lists = homeData?.lists.orEmpty(),
                userImageUrl = homeData?.user?.photoUrl,
                onAvatarClick = redirectToProfile,
                onClickAddData = redirectToCreateList,
                filterLists = { viewModel.onSearchQueryChanged(it) },
                onClickShowDetail = redirectToListDetail
            )
        }
    }
}