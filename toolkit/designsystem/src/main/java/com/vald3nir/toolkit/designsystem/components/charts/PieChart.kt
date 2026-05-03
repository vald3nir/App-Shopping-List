package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.charts.model.ItemChartDTO
import com.vald3nir.toolkit.designsystem.components.charts.model.sum
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ToolkitPieChart(modifier: Modifier = Modifier, label: String = "", data: List<ItemChartDTO>, colors: List<Color>, labelsColor: Int = android.graphics.Color.BLACK) {
    val totalSum = data.sum()
    var startAngle = -90f
    Canvas(modifier = modifier.size(300.dp)) {
        val radius = size.minDimension / 2
        val labelRadius = radius * 0.6f

        data.forEachIndexed { index, item ->
            val sweepAngle = (item.value / totalSum) * 360f
            val percentage = (item.value / totalSum) * 100
            val angleInDegrees = startAngle + sweepAngle / 2
            val angleInRadians = Math.toRadians(angleInDegrees.toDouble())
            val x = center.x + labelRadius * cos(angleInRadians).toFloat()
            val y = center.y + labelRadius * sin(angleInRadians).toFloat()

            drawArc(
                color = colors[index], startAngle = startAngle, sweepAngle = sweepAngle, useCenter = true
            )

            drawIntoCanvas { canvas ->
                val paint = android.graphics.Paint().apply {
                    color = labelsColor
                    textSize = 36f
                    textAlign = android.graphics.Paint.Align.CENTER
                    isAntiAlias = true
                }
                canvas.nativeCanvas.drawText(item.label, x, y, paint)
                canvas.nativeCanvas.drawText("${percentage.toInt()}%", x + 10, y - 30, paint)
            }
            startAngle += sweepAngle
        }

        drawIntoCanvas { canvas ->
            val paint = android.graphics.Paint().apply {
                color = labelsColor
                textSize = 48f
                textAlign = android.graphics.Paint.Align.CENTER
                isAntiAlias = true
            }
            canvas.nativeCanvas.drawText(label, center.x, center.y, paint)
        }
    }
}