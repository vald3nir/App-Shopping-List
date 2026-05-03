package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitNavigationBar(
    modifier: Modifier = Modifier,
    labels: List<String>,
    icons: List<ImageVector>,
    selectedIcons: List<ImageVector>? = null,
    selectedTab: Int = 0,
    onClick: (index: Int) -> Unit = {}
) {
    NavigationBar(
        modifier = modifier,
        contentColor = NavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp
    ) {
        labels.forEachIndexed { index, label ->
            ToolkitNavigationBarItem(
                icon = {
                    ToolkitIcon(
                        imageVector = icons[index],
                        contentDescription = label,
                    )
                },
                selectedIcon = {
                    ToolkitIcon(
                        imageVector = (selectedIcons ?: icons)[index],
                        contentDescription = label,
                    )
                },
                label = { Text(label) },
                selected = index == selectedTab,
                onClick = { onClick(index) },
            )
        }
    }
}

@Composable
private fun RowScope.ToolkitNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NavigationDefaults.navigationContentColor(),
            selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NavigationDefaults.navigationContentColor(),
            indicatorColor = NavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    val labels = listOf("For you", "Saved", "Interests")
    val icons = listOf(
        ToolkitIconCatalog.UpcomingBorder,
        ToolkitIconCatalog.BookmarksBorder,
        ToolkitIconCatalog.Grid3x3,
    )
    val selectedIcons = listOf(
        ToolkitIconCatalog.Upcoming,
        ToolkitIconCatalog.Bookmarks,
        ToolkitIconCatalog.Grid3x3,
    )
    ToolkitPreviewContainer(modifier = Modifier.size(width = 300.dp, height = 150.dp)) {
        ToolkitNavigationBar(
            labels = labels,
            icons = icons,
            selectedIcons = selectedIcons,
        )
    }
}