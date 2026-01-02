package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import java.time.LocalDate

@ThemePreviews
@Composable
private fun Preview() {
    var month by remember { mutableIntStateOf(LocalDate.now().monthValue) }
    var year by remember { mutableIntStateOf(LocalDate.now().year) }
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column {
                ToolkitSelectorMonthYear(month = month, year = year, onMonthYearChange = { newMonth, newYear ->
                    month = newMonth
                    year = newYear
                })
                DefaultSpaceHeight()
                ToolkitSelectorYear(year = year, onYearChange = { newYear ->
                    year = newYear
                })
            }
        }
    }
}