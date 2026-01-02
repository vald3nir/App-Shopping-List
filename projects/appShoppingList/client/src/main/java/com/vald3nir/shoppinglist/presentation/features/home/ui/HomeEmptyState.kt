package com.vald3nir.shoppinglist.presentation.features.home.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.presentation.components.buildTopBarWithAvatar
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.templates.ToolkitBaseContent
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen

@Composable
internal fun HomeEmptyState(
    userImageUrl: String? = null,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onAvatarClick: () -> Unit = {},
    onClickAddData: () -> Unit = {}
) {
    ToolkitBaseContent(
        snackBarHostState = snackBarHostState,
        topBar = buildTopBarWithAvatar(
            title = stringResource(R.string.home_title),
            userImageUrl = userImageUrl,
            onAvatarClick = onAvatarClick
        )
    ) {
        ToolkitEmptyStateScreen(
            title = stringResource(R.string.no_registered_lists),
            btnText = stringResource(R.string.add_item),
            imageVector = ToolkitIconCatalog.Inbox,
            onClickBtn = onClickAddData,
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewEmptyState() {
    AppTheme {
        ToolkitBackground {
            HomeEmptyState()
        }
    }
}