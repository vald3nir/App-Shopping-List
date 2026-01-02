package com.vald3nir.shoppinglist.presentation.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitCard
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun HomeListsSection(title: String, date: String, onClick: () -> Unit) {
    ToolkitCard(
        modifier = Modifier.padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingXs),
        onClick = onClick,
        content = {
            Row(
                modifier = Modifier
                    .padding(ToolkitSpacingMd)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ToolkitText(text = title, style = ToolkitTextStyle.TitleSmall)
                    ToolkitSpaceHeight(ToolkitSpacingSm)
                    ToolkitText(text = date, style = ToolkitTextStyle.BodyMedium)
                }
                ToolkitIcon(imageVector = ToolkitIconCatalog.ArrowIndicatorRight)
            }
        }
    )
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground {
            Column {
                HomeListsSection(title = "Lista de compras", date = "28/01/2026") { }
                HomeListsSection(title = "Lista de compras", date = "28/01/2026") { }
                HomeListsSection(title = "Lista de compras", date = "28/01/2026") { }
            }
        }
    }
}