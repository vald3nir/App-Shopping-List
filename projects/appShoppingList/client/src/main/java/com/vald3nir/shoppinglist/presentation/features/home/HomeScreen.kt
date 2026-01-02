package com.vald3nir.shoppinglist.presentation.features.home

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.shoppinglist.presentation.components.ScreenLoading
import com.vald3nir.shoppinglist.presentation.features.home.ui.HomeBottomSheet
import com.vald3nir.shoppinglist.presentation.features.home.ui.HomeEmptyState
import com.vald3nir.shoppinglist.presentation.features.home.ui.HomeScreenContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.orFalse

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    redirectToCreateList: () -> Unit,
    redirectToListDetail: (shoppingListID: Long?) -> Unit,
    redirectToProfile: () -> Unit,
    redirectToAuth: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val message by viewModel.uiMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val searchQuery by viewModel.searchQuery.collectAsState()
    val homeData by viewModel.homeDataFlow.collectAsStateWithLifecycle()
    var showMenu by remember { mutableStateOf(false) }

    val onClickAvatar: () -> Unit = {
        if (homeData?.hasUserLogged().orFalse()) {
            showMenu = true
//            redirectToProfile()
        } else {
            redirectToAuth()
        }
    }

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            snackBarHostState.showSnackbar(message = message, duration = SnackbarDuration.Short)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.syncLists()
    }

    when (uiState) {
        is BaseUiState.LoadingState -> {
            ScreenLoading()
            return
        }

        is BaseUiState.EmptySate -> {
            HomeEmptyState(
                userImageUrl = homeData?.user?.photoUrl,
                onClickAddData = redirectToCreateList,
                onAvatarClick = onClickAvatar,
            )
            return
        }

        is BaseUiState.ShowState -> {
            HomeScreenContent(
                searchQuery = searchQuery,
                lists = homeData?.lists.orEmpty(),
                userImageUrl = homeData?.user?.photoUrl,
                onAvatarClick = onClickAvatar,
                onClickAddData = redirectToCreateList,
                filterLists = { viewModel.onSearchQueryChanged(it) },
                onClickShowDetail = redirectToListDetail
            )
        }

        else -> Unit
    }

    if (showMenu) {
        HomeBottomSheet(
            onClickChangeThema = {},
            onClickLogout = {},
            onDismissRequest = { showMenu = false }
        )
    }
}