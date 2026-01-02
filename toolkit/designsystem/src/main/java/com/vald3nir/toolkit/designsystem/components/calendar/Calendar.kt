package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import java.time.LocalDate
import java.util.Calendar

@Composable
fun ToolkitCalendar(
    modifier: Modifier = Modifier,
    month: Int,
    year: Int,
    selectedDay: Int = -1,
    labels: MutableList<String> = emptyList<String>().toMutableList(),
    onMonthYearChanged: (Int, Int) -> Unit,
    onDaySelected: (Int) -> Unit
) {
    val totalDays = getSizeDaysInMonth(month, year)
    val firstDayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value % 7 // 0 for sunday
    val days = List(firstDayOfWeek) { "" } + (1..totalDays).map { it.toString() }
    if (labels.size < totalDays) {
        labels.addAll(List(totalDays - labels.size) { "" })
    }
    Column(modifier = modifier.padding(8.dp)) {
        ToolkitSelectorMonthYear(month = month, year = year, onMonthYearChange = onMonthYearChanged)
        Spacer(modifier = Modifier.height(16.dp))
        StripeDayOfWeek()
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(7), modifier = modifier
        ) {
            items(days.size) { index ->
                val day = days[index]
                if (day.isEmpty()) return@items
                CalendarCell(
                    day = day, text = labels[day.toInt() - 1], selectedDay = selectedDay, onDaySelected = onDaySelected
                )
            }
        }
    }
}

@Composable
private fun StripeDayOfWeek() {
    val daysOfWeek = listOf("S", "T", "Q", "Q", "S", "S", "D")
    Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp), contentAlignment = Alignment.Center
            ) {
                ToolkitText(text = day, style = ToolkitTextStyle.LabelSmall, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
private fun CalendarCell(day: String, text: String = "X", selectedDay: Int, onDaySelected: (Int) -> Unit = {}) {
    val isDaySelected = isDaySelected(day, selectedDay)
    val backgroundColor = if (isDaySelected) Color.Blue else Color.LightGray
    val textColor = if (isDaySelected) Color.White else Color.Black
    Column(
        modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolkitText(text = day, style = ToolkitTextStyle.LabelSmall, textAlign = TextAlign.Center)
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = backgroundColor, shape = RoundedCornerShape(8.dp)
                )
                .clickable(enabled = day.isNotEmpty()) {
                    if (day.isNotEmpty()) onDaySelected(day.toInt())
                }, contentAlignment = Alignment.Center
        ) {
            ToolkitText(text = text, style = ToolkitTextStyle.LabelSmall, textAlign = TextAlign.Center, textColor = textColor)
        }
    }
}

@Composable
private fun isDaySelected(day: String, selectedDay: Int) = day.isNotEmpty() && (day.toInt() == selectedDay)

private fun getSizeDaysInMonth(month: Int, year: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}