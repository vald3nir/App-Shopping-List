package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.charts.model.ItemChartDTO
import com.vald3nir.toolkit.designsystem.components.charts.utils.formatChartValue
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitLineChart(
    modifier: Modifier = Modifier,
    title: String,
    data: List<ItemChartDTO>
) {
    if (data.isEmpty()) return

    val scrollState = rememberScrollState()
    val textPaint = remember {
        android.graphics.Paint().apply {
            color = android.graphics.Color.GRAY
            textSize = 30f
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        ToolkitText(
            text = title,
            style = ToolkitTextStyle.TitleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Container de Scroll
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            // Definimos uma largura dinâmica: 80dp por ponto de dado
            // Ou o tamanho da tela, o que for maior.
            val chartDynamicWidth = (data.size * 80).dp.coerceAtLeast(400.dp)

            Canvas(
                modifier = Modifier
                    .width(chartDynamicWidth)
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                val width = size.width
                val height = size.height
                val paddingLeft = 80f // Espaço maior para os valores do eixo Y não sumirem no scroll
                val paddingBottom = 60f
                val chartWidth = width - (paddingLeft + 40f)
                val chartHeight = height - (paddingBottom + 40f)

                val maxValue = data.maxOf { it.value }.coerceAtLeast(1.0f)
                val minValue = 0.0

                // 1. Eixos (O Eixo Y fixo requer uma lógica complexa, aqui ele rola junto)
                drawLine(
                    color = Color.LightGray,
                    start = Offset(paddingLeft, height - paddingBottom),
                    end = Offset(width, height - paddingBottom),
                    strokeWidth = 2f
                )

                // 2. Rótulos do Eixo Y (Valores)
                val ySteps = 5
                for (i in 0..ySteps) {
                    val fraction = i.toFloat() / ySteps
                    val yValue = maxValue * fraction
                    val yPos = (height - paddingBottom) - (fraction * chartHeight)

                    drawContext.canvas.nativeCanvas.drawText(
                        formatChartValue(yValue),
                        paddingLeft - 20f,
                        yPos + 10f,
                        textPaint.apply { textAlign = android.graphics.Paint.Align.RIGHT }
                    )
                }

                // 3. Cálculo de Pontos e Eixo X
                val spaceBetweenPoints = chartWidth / (data.size - 1).coerceAtLeast(1)

                val points = data.mapIndexed { index, data ->
                    val x = paddingLeft + (index * spaceBetweenPoints)
                    val normalizedPower = (data.value - minValue) / (maxValue - minValue)
                    val y = (height - paddingBottom) - (normalizedPower.toFloat() * chartHeight)

                    // Rótulos do Eixo X (Datas)
                    drawContext.canvas.nativeCanvas.drawText(
                        data.label,
                        x,
                        height - 10f,
                        textPaint.apply { textAlign = android.graphics.Paint.Align.CENTER }
                    )

                    Offset(x, y)
                }

                // 4. Desenhar as linhas do gráfico
                for (i in 0 until points.size - 1) {
                    drawLine(
                        color = Color(0xFF4A90E2),
                        start = points[i],
                        end = points[i + 1],
                        strokeWidth = 4f
                    )
                }

                // 5. Desenhar os círculos
                points.forEach { point ->
                    drawCircle(color = Color(0xFF4A90E2), radius = 6f, center = point)
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ToolkitLineChart(
            title = "Titulo",
            data = listOf(
                ItemChartDTO(700f, "Jan"),
                ItemChartDTO(200f, "Fev"),
                ItemChartDTO(300f, "Mar"),
            )
        )
    }
}