package com.vald3nir.toolkit.designsystem.components.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitLinkButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

data class SimpleItemListDTO(
    val icon: ImageVector,
    val title: String,
    val btnLabel: String,
    val onClickListener: () -> Unit
)

@Composable
fun ToolkitSimpleList(items: List<SimpleItemListDTO>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { _, item ->
            SimpleItemList(item)
        }
    }
}

@Composable
private fun SimpleItemList(item: SimpleItemListDTO, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { item.onClickListener() }
    ) {
        ToolkitIcon(
            imageVector = item.icon,
            modifier = modifier.size(40.dp)
        )

        Spacer(modifier = modifier.width(8.dp))

        ToolkitText(text = item.title, style = ToolkitTextStyle.TitleSmall, modifier = modifier.weight(1f))

        ToolkitLinkButton(
            onClick = { item.onClickListener() },
            label = item.btnLabel,
        )
    }
}