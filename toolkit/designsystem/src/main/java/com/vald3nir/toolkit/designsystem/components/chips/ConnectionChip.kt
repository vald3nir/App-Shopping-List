package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitConnectionChip(isConnected: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingXs),
        modifier = Modifier
            .background(
                color = if (isConnected) Color(0xFF1B3A2A) else Color(0xFF3A1B1B),
                shape = RoundedCornerShape(ToolkitSpacingLg)
            )
            .padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingXs)
    ) {
        val pulseAlpha by rememberInfiniteTransition(label = "pulse").animateFloat(
            initialValue = 0.4f, targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
            label = "alpha"
        )
        Box(
            modifier = Modifier
                .size(ToolkitSpacingSm)
                .background(
                    color = if (isConnected) Color(0xFF4ADE80).copy(alpha = pulseAlpha) else Color(0xFFF87171),
                    shape = CircleShape
                )
        )
        ToolkitText(
            text = if (isConnected) "Online" else "Offline",
            textColor = if (isConnected) Color(0xFF4ADE80) else Color(0xFFF87171),
            style = ToolkitTextStyle.BodyMedium
        )
    }
}