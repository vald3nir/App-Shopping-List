package com.vald3nir.toolkit.designsystem.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.vald3nir.toolkit.designsystem.theme.text.ToolkitTypography

enum class ToolkitTextStyle(val textStyle: TextStyle) {
    DisplayLarge(ToolkitTypography.displayLarge),
    DisplayMedium(ToolkitTypography.displayMedium),
    DisplaySmall(ToolkitTypography.displaySmall),

    HeadlineLarge(ToolkitTypography.headlineLarge),
    HeadlineMedium(ToolkitTypography.headlineMedium),
    HeadlineSmall(ToolkitTypography.headlineSmall),

    TitleLarge(ToolkitTypography.titleLarge),
    TitleMedium(ToolkitTypography.titleMedium),
    TitleSmall(ToolkitTypography.titleSmall),

    BodyLarge(ToolkitTypography.bodyLarge),
    BodyMedium(ToolkitTypography.bodyMedium),
    BodySmall(ToolkitTypography.bodySmall),

    LabelLarge(ToolkitTypography.labelLarge),
    LabelMedium(ToolkitTypography.labelMedium),
    LabelSmall(ToolkitTypography.labelSmall),
}

@Composable
fun ToolkitText(
    text: String,
    style: ToolkitTextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color? = null,
) {
    style.textStyle.BuiltText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        textColor = textColor
    )
}

@Composable
private fun TextStyle.BuiltText(modifier: Modifier = Modifier, text: String, textAlign: TextAlign, textColor: Color?) = Text(
    modifier = modifier,
    text = text,
    style = this.copy(textAlign = textAlign, color = textColor ?: this.color)
)