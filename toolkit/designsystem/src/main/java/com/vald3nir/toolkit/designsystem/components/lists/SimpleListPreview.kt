package com.vald3nir.toolkit.designsystem.components.lists

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
                val items = ArrayList<SimpleItemListDTO>()
                List(5) {
                    items.add(
                        SimpleItemListDTO(icon = ToolkitIconCatalog.Downloading, title = "Item $it", btnLabel = "Ver", onClickListener = {})
                    )
                }
                ToolkitSimpleList(items)
            }
        }
    }
}