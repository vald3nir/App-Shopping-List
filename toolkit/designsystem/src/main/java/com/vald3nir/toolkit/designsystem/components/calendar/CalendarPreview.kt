package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            ToolkitCalendar(
                month = 2,
                year = 2025,
                selectedDay = 5,
                modifier = Modifier.fillMaxWidth(),
                onMonthYearChanged = { month, year ->
                    println("Month: $month, Year: $year")
                },
                onDaySelected = {}
            )
        }
    }
}