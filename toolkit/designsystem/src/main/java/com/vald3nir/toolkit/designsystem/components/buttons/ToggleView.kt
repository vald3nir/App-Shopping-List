package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog

@Composable
fun ToolkitToggleView(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    compactText: @Composable () -> Unit,
    expandedText: @Composable () -> Unit,
) {
    TextButton(
        onClick = { onExpandedChange(!expanded) },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onBackground),
        contentPadding = ToolkitToggleViewDefaults.ViewToggleButtonContentPadding,
    ) {
        ToolkitToggleViewContent(
            text = if (expanded) expandedText else compactText,
            trailingIcon = {
                Icon(imageVector = if (expanded) ToolkitIconCatalog.ViewDay else ToolkitIconCatalog.ShortText, contentDescription = null)
            },
        )
    }
}

@Composable
private fun ToolkitToggleViewContent(text: @Composable () -> Unit, trailingIcon: @Composable (() -> Unit)? = null) {
    Box(Modifier.padding(end = if (trailingIcon != null) ButtonDefaults.IconSpacing else 0.dp)) {
        ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
            text()
        }
    }
    if (trailingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            trailingIcon()
        }
    }
}

private object ToolkitToggleViewDefaults {
    val ViewToggleButtonContentPadding = PaddingValues(
        start = 16.dp,
        top = 8.dp,
        end = 12.dp,
        bottom = 8.dp,
    )
}
