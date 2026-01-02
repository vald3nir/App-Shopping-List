package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.extensions.getRandomColors
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val data = listOf(
                    ItemChartDTO(750f, "Jan"),
                    ItemChartDTO(200f, "Fev"),
                    ItemChartDTO(300f, "Mar"),
                )
                val colors = getRandomColors(data.size)
                ToolkitBarChart(colors = colors, data = data)
                DefaultSpaceHeight()
                ToolkitPieChart(colors = colors, data = data, label = "Exemplo")
                DefaultSpaceHeight()
                ToolkitSpeedometer(currentValue = 75f, maxValue = 100f)
            }
        }
    }
}