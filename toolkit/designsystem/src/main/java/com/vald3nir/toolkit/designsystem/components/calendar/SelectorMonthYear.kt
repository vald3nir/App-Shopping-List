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
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ToolkitSelectorMonthYear(month: Int, year: Int, onMonthYearChange: (Int, Int) -> Unit) {

    val months = (1..12).map {
        LocalDate.of(year, it, 1).month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

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
                val newMonth = if (month == 1) 12 else month - 1
                val newYear = if (month == 1) year - 1 else year
                onMonthYearChange(newMonth, newYear)
            }
        )

        ToolkitText(text = "${months[month - 1]} $year", style = ToolkitTextStyle.TitleMedium)

        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronRight,
            onClick = {
                val newMonth = if (month == 12) 1 else month + 1
                val newYear = if (month == 12) year + 1 else year
                onMonthYearChange(newMonth, newYear)
            }
        )
    }
}