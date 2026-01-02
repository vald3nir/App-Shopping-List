package com.vald3nir.shoppinglist.presentation.features.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.menus.ToolkitBottomSheet
import com.vald3nir.toolkit.designsystem.components.menus.ToolkitItemBottomSheet

@Composable
fun HomeBottomSheet(
    onClickChangeThema: () -> Unit,
    onClickLogout: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    ToolkitBottomSheet(
        items = listOf(
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIconCatalog.Palette,
                title = stringResource(R.string.home_menu_btn_thema),
                onClick = {
                    onClickChangeThema()
                    onDismissRequest()
                }
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIconCatalog.Logout,
                title = stringResource(R.string.home_menu_btn_logout),
                onClick = {
                    onClickLogout()
                    onDismissRequest()
                }
            ),
        ),
        onDismissRequest = onDismissRequest
    )
}