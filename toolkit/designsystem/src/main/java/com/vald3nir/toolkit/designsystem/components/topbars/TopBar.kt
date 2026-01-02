package com.vald3nir.toolkit.designsystem.components.topbars

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitTopBar(
    modifier: Modifier = Modifier,
    topAppBarColors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    title: String? = null,
    @StringRes titleRes: Int? = null,
    textCenterAligned: Boolean = false,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    rightIcon: ImageVector? = null,
    onClickRightIcon: () -> Unit = {},
) {
    val titleContent: @Composable () -> Unit = {
        (titleRes?.let { stringResource(it) } ?: title)?.let {
            ToolkitText(text = it, style = ToolkitTextStyle.TitleMedium)
        }
    }
    val leftIconContent: @Composable () -> Unit = {
        leftIcon?.BuildIconButton(
            onClick = onClickLeftIcon,
            tint = iconTint,
        )
    }
    val rightIconContent: @Composable RowScope.() -> Unit = {
        rightIcon?.BuildIconButton(
            onClick = onClickRightIcon,
            tint = iconTint,
        )
    }
    if (textCenterAligned) {
        CenterAlignedTopAppBar(
            title = titleContent,
            navigationIcon = leftIconContent,
            actions = rightIconContent,
            colors = topAppBarColors,
            modifier = modifier,
        )
    } else {
        TopAppBar(
            title = titleContent,
            navigationIcon = leftIconContent,
            actions = rightIconContent,
            colors = topAppBarColors,
            modifier = modifier,
        )
    }
}