package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitVerticalBar(
    label: String,
    value: String,
    maxHeight: Dp = 300.dp,
    barHeight: Dp = 150.dp,
    barColor: Color,
    barWidth: Dp = 48.dp,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .height(maxHeight)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ToolkitText(text = value, style = ToolkitTextStyle.LabelSmall, modifier = Modifier.align(Alignment.CenterHorizontally))

        Canvas(modifier = Modifier.size(barWidth, barHeight)) {
            val barSize = androidx.compose.ui.geometry.Size(barWidth.toPx(), barHeight.toPx())
            drawRect(
                color = barColor,
                size = barSize
            )
        }

        ToolkitText(text = label, style = ToolkitTextStyle.LabelSmall, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}