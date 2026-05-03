package com.vald3nir.toolkit.designsystem.components.texts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitTag(
    modifier: Modifier = Modifier,
    followed: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        val containerColor = if (followed) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = ToolkitTagDefaults.UNFOLLOWED_TOPIC_TAG_CONTAINER_ALPHA)
        }
        TextButton(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ToolkitTagDefaults.DISABLED_TOPIC_TAG_CONTAINER_ALPHA),
            )
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}

private object ToolkitTagDefaults {
    const val UNFOLLOWED_TOPIC_TAG_CONTAINER_ALPHA = 0.5f
    const val DISABLED_TOPIC_TAG_CONTAINER_ALPHA = 0.12f
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(100.dp)) {
        ToolkitTag(followed = true, onClick = {}) {
            Text("Topic".uppercase())
        }
    }
}