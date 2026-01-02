package com.vald3nir.toolkit.designsystem.components.icons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage

@Composable
fun ToolkitIconAvatar(
    modifier: Modifier,
    userImageUrl: String?,
    contentDescription: String? = "Avatar",
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {
    IconButton(onClick = onClick, modifier = modifier) {
        SubcomposeAsyncImage(
            model = userImageUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(CircleShape),
            loading = {
                Icon(
                    imageVector = ToolkitIconCatalog.AccountCircle,
                    contentDescription = contentDescription,
                    tint = tint
                )
            },
            error = {
                Icon(
                    imageVector = ToolkitIconCatalog.AccountCircle,
                    contentDescription = contentDescription,
                    tint = tint
                )
            }
        )
    }
}