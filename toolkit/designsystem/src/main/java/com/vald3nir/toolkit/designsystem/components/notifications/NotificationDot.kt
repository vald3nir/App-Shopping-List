package com.vald3nir.toolkit.designsystem.components.notifications

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.notificationDot(radius: Dp = 5.dp, color: Color? = null, spacing: Dp = 6.dp) = this
    .padding(spacing)
    .composed {
        val targetColor = color ?: MaterialTheme.colorScheme.tertiary
        val radiusPx = with(androidx.compose.ui.platform.LocalDensity.current) { radius.toPx() }
        drawWithContent {
            drawContent()
            val centerX = size.width - (radiusPx * .2f)
            val centerY = radiusPx * .2f
            drawCircle(
                color = targetColor, radius = radiusPx, center = Offset(centerX, centerY)
            )
        }
    }