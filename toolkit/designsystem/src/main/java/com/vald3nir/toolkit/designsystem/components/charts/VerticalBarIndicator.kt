package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun ToolkitVerticalBarIndicator(modifier: Modifier, maxPower: Int) {
    val isDarkMode = isSystemInDarkTheme()
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasHeight = size.height
            val numberOfTicks = 5
            val tickSpacing = (maxPower / numberOfTicks)
            val tickHeight = (canvasHeight) / numberOfTicks
            for (i in 0..numberOfTicks) {
                val yPosition = canvasHeight - (i * tickHeight)
                drawLine(
                    color = Color.White,
                    start = androidx.compose.ui.geometry.Offset(0f, yPosition),
                    end = androidx.compose.ui.geometry.Offset(20f, yPosition),
                    strokeWidth = 2f
                )
                drawIntoCanvas { canvas ->
                    val paint = android.graphics.Paint().apply {
                        textSize = 24f
                        color = if (isDarkMode) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                    }
                    canvas.nativeCanvas.drawText(
                        "${i * tickSpacing}",
                        30f,
                        yPosition + 10f,
                        paint
                    )
                }
            }
        }
    }
}