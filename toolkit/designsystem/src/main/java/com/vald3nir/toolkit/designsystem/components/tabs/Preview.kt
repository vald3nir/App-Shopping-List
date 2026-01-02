package com.vald3nir.toolkit.designsystem.components.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitTabRow(selectedTabIndex = 0) {
                    listOf("Topics", "People").forEachIndexed { index, title ->
                        ToolkitTab(
                            selected = index == 0,
                            onClick = { },
                            text = title,
                        )
                    }
                }
                ToolkitTabRow(selectedTabIndex = 0) {
                    listOf(
                        "Topics" to ToolkitIconCatalog.Downloading,
                        "People" to ToolkitIconCatalog.AccountCircle
                    ).forEachIndexed { index, item ->
                        ToolkitTab(
                            selected = index == 0,
                            onClick = { },
                            text = item.first,
                            icon = item.second,
                        )
                    }
                }
            }
        }
    }
}