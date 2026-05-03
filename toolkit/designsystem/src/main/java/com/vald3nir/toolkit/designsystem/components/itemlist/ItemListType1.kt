package com.vald3nir.toolkit.designsystem.components.itemlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

data class ItemListType1DTO(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val iconTint: Color,
    val iconBackground: Color = Color(0xFF3C4043),
)

@Composable
fun ItemListType1(modifier: Modifier = Modifier, item: ItemListType1DTO, onclick: (ItemListType1DTO) -> Unit = {}) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onclick(item) }
            .padding(vertical = ToolkitSpacingMd),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DeviceIcon(item)
        ToolkitSpaceWidth()
        Column(modifier = Modifier.weight(1f)) {
            ToolkitText(
                text = item.title,
                style = ToolkitTextStyle.TitleLarge
            )
            ToolkitSpaceHeight(2.dp)
            ToolkitText(
                text = item.description,
                style = ToolkitTextStyle.TitleSmall
            )
        }
    }
    ToolkitDivider()
}

@Composable
private fun DeviceIcon(item: ItemListType1DTO) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(item.iconBackground)
    ) {
        ToolkitIcon(
            imageVector = item.icon,
            tint = item.iconTint,
            modifier = Modifier.size(24.dp)
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        val devices = listOf(
            ItemListType1DTO(title = "titulo 1", description = "descrição 1", icon = ToolkitIconCatalog.PhoneAndroid, iconTint = Color(0xFF4285F4)),
            ItemListType1DTO(title = "titulo 2", description = "descrição 2", icon = ToolkitIconCatalog.DirectionsCar, iconTint = Color(0xFFDB4437)),
            ItemListType1DTO(title = "titulo 3", description = "descrição 3", icon = ToolkitIconCatalog.PedalBike, iconTint = Color(0xFFF4B400)),
            ItemListType1DTO(title = "titulo 4", description = "descrição 4", icon = ToolkitIconCatalog.Backpack, iconTint = Color(0xFF0F9D58)),
            ItemListType1DTO(title = "titulo 5", description = "descrição 5", icon = ToolkitIconCatalog.ShoppingCart, iconTint = Color(0xFF4285F4))
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
        ) {
            items(devices.size) { index ->
                ItemListType1(item = devices[index])
            }
        }
    }
}

