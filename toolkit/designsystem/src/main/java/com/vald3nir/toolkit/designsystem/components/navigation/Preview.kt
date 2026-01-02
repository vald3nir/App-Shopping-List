package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun BarPreview() {
    val items = listOf("For you", "Saved", "Interests")
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

    ToolkitTheme {
        ToolkitNavigationBar {
            items.forEachIndexed { index, item ->
                ToolkitNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun RailPreview() {
    val items = listOf("For you", "Saved", "Interests")
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

    ToolkitTheme {
        ToolkitNavigationRail {
            items.forEachIndexed { index, item ->
                ToolkitNavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun DrawerPreviewOpen() {
    ToolkitTheme {
        ToolkitDrawer(
            contentTitle = "Drawer Title",
            initialDrawerState = DrawerValue.Open,
            menuContent = DrawerPreviewContent(),
            content = { }
        )
    }
}

@ThemePreviews
@Composable
private fun DrawerPreviewClosed() {
    ToolkitTheme {
        ToolkitDrawer(
            contentTitle = "Drawer Title",
            initialDrawerState = DrawerValue.Closed,
            menuContent = DrawerPreviewContent(),
            content = { }
        )
    }
}

@Composable
private fun DrawerPreviewContent(): @Composable() (ColumnScope.() -> Unit) = {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(12.dp))
        Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
        DefaultSpaceHeight()

        Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
        NavigationDrawerItem(
            label = { Text("Item 1") },
            selected = false,
            onClick = { /* Handle click */ }
        )
        NavigationDrawerItem(
            label = { Text("Item 2") },
            selected = false,
            onClick = { /* Handle click */ }
        )

        DefaultSpaceHeight()

        Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
            badge = { Text("20") }, // Placeholder
            onClick = { /* Handle click */ }
        )
        NavigationDrawerItem(
            label = { Text("Help and feedback") },
            selected = false,
            icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
            onClick = { /* Handle click */ },
        )
        Spacer(Modifier.height(12.dp))
    }
}