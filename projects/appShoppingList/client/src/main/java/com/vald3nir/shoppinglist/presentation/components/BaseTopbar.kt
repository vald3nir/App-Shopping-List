package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBar
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBarWithAvatar

@Composable
fun buildTopBarWithAvatar(title: String, userImageUrl: String?, onAvatarClick: () -> Unit): @Composable () -> Unit = {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {
        ToolkitTopBarWithAvatar(
            title = title,
            textCenterAligned = true,
            userImageUrl = "https://raw.githubusercontent.com/vald3nir/App-Shopping-List/develop/docs/drawables/logo.png",
//            userImageUrl = userImageUrl,
//            onAvatarClick = onAvatarClick
        )
    }
}

@Composable
fun buildTopBar(
    title: String,
    onBackPressed: () -> Unit,
    extraIcon: ImageVector? = null,
    onClickExtraIcon: () -> Unit = {},
): @Composable () -> Unit = {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {
        ToolkitTopBar(
            title = title,
            textCenterAligned = true,
            leftIcon = ToolkitIconCatalog.ArrowBack,
            onClickLeftIcon = onBackPressed,
            rightIcon = extraIcon,
            onClickRightIcon = onClickExtraIcon,
        )
    }
}