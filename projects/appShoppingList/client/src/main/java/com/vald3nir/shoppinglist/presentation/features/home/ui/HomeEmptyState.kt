package com.vald3nir.shoppinglist.presentation.features.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBarWithAvatar
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen

@Composable
internal fun HomeEmptyState(
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