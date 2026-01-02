package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitSelectorYear(year: Int, onYearChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronLeft,
            onClick = {
                onYearChange(year - 1)
            }
        )

        ToolkitText(text = "$year", style = ToolkitTextStyle.TitleMedium)

        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronRight,
            onClick = {
                onYearChange(year + 1)
            }
        )
    }
}