package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBar

@Composable
fun AppTopBar(
    title: String,
    leftIcon: ImageVector? = ToolkitIconCatalog.ArrowBack,
    onBackPressed: () -> Unit,
    extraIcon: ImageVector? = null,
    onClickExtraIcon: () -> Unit = {},
) = ToolkitTopBar(
    title = title,
    textCenterAligned = true,
    leftIcon = leftIcon,
    onClickLeftIcon = onBackPressed,
    rightIcon = extraIcon,
    onClickRightIcon = onClickExtraIcon,
)