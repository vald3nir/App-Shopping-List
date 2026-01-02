package com.vald3nir.toolkit.designsystem.components.icons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ToolkitIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    tint: Color? = null,
    onClick: (() -> Unit)? = null,
) {
    if (onClick != null) {
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint ?: LocalContentColor.current
            )
        }
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint ?: LocalContentColor.current
        )
    }
}

@Composable
fun ImageVector.BuildIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.LightGray,
    contentDescription: String? = null
) = ToolkitIcon(
    modifier = modifier,
    imageVector = this,
    contentDescription = contentDescription,
    tint = tint,
)

@Composable
fun ImageVector.BuildIconButton(
    modifier: Modifier = Modifier,
    tint: Color = Color.LightGray,
    contentDescription: String? = null,
    onClick: () -> Unit,
) = ToolkitIcon(
    modifier = modifier,
    imageVector = this,
    contentDescription = contentDescription,
    tint = tint,
    onClick = onClick
)