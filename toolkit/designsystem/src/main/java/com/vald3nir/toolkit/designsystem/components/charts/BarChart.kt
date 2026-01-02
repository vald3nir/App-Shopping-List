package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth

@Composable
fun ToolkitBarChart(
    data: List<ItemChartDTO>,
    colors: List<Color>,
    onClickBar: (index: Int) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val maxValue = data.maxOf { it.value }
    val maxHeight = 300.dp
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            ToolkitVerticalBarIndicator(
                maxPower = maxValue.toInt(),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .height(250.dp)
                    .width(20.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(maxHeight)
                    .padding(start = 35.dp)
                    .horizontalScroll(scrollState)
            ) {
                data.forEachIndexed { index, dataPoint ->
                    ToolkitVerticalBar(
                        barColor = colors[index % colors.size],
                        barHeight = 250.dp.times((dataPoint.value / maxValue)),
                        maxHeight = maxHeight,
                        label = dataPoint.label,
                        value = dataPoint.value.toString(),
                        onClick = { onClickBar(index) }
                    )
                    if (index < data.size - 1) {
                        DefaultSpaceWidth()
                    }
                }
            }
        }
    }
}