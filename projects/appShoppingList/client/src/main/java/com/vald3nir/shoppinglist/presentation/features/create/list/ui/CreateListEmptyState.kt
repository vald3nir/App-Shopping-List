package com.vald3nir.shoppinglist.presentation.features.create.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.components.AppTopBar
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen

@Composable
internal fun CreateListEmptyState(
    onClickAddData: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    ToolkitColumn {
        AppTopBar(
            title = stringResource(R.string.new_list_title),
            onBackPressed = onBackPressed,
        )
        ToolkitEmptyStateScreen(
            title = stringResource(R.string.create_list_empty_state_message),
            btnText = stringResource(R.string.create_list_btn_add_items),
            imageVector = ToolkitIconCatalog.ShoppingCart,
            onClickBtn = onClickAddData,
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewEmptyState() {
    AppTheme {
        ToolkitBackground {
            CreateListEmptyState()
        }
    }
}