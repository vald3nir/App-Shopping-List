package com.vald3nir.shoppinglist.presentation.features.details.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun CartDetailsRow(label: String, value: String) {
    val style = ToolkitTextStyle.TitleSmall
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = ToolkitSpacingMd, top = ToolkitSpacingSm, end = ToolkitSpacingMd),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ToolkitText(text = label, style = style)
        ToolkitText(text = value, style = style)
    }
}