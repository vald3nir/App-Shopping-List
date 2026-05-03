package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun ToolkitDeleteButton(
    confirmState: Boolean = false,
    onClickRemove: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickShowConfirm: () -> Unit = {},
) {
    AnimatedContent(
        targetState = confirmState,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
    ) { confirming ->
        if (confirming) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                ToolkitIcon(
                    modifier = Modifier.background(Color(0xFFF87171).copy(alpha = 0.15f), CircleShape),
                    imageVector = ToolkitIconCatalog.Check,
                    onClick = onClickRemove
                )
                ToolkitIcon(
                    modifier = Modifier,
                    imageVector = ToolkitIconCatalog.Close,
                    onClick = onClickCancel
                )
            }
        } else {
            ToolkitIcon(
                modifier = Modifier,
                imageVector = ToolkitIconCatalog.Delete,
                onClick = onClickShowConfirm
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitPreviewContainer(modifier = Modifier.size(100.dp)) {
            var confirmState by remember { mutableStateOf(false) }
            ToolkitDeleteButton(
                confirmState = confirmState,
                onClickRemove = { confirmState = false },
                onClickCancel = { confirmState = false },
                onClickShowConfirm = { confirmState = true },
            )
        }
    }
}
