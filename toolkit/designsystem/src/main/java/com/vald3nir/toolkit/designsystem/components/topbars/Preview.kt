package com.vald3nir.toolkit.designsystem.components.topbars

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column {
                ToolkitTopBar(
                    title = "Exemplo",
                    textCenterAligned = true,
                    leftIcon = ToolkitIconCatalog.ArrowBack,
                    rightIcon = ToolkitIconCatalog.Menu,
                )
                ToolkitTopBarWithAvatar(
                    title = "Exemplo com avatar",
                    textCenterAligned = true,
                    leftIcon = ToolkitIconCatalog.ArrowBack,
                    userImageUrl = "null"
                )
            }
        }
    }
}