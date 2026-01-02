package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                ToolkitBaseButton(onClick = {}, text = "Test button")

                AlertButton(onClick = {}, text = "Red Button")

                ToolkitOutlinedButton(onClick = {}, text = "Test button")

                ToolkitBaseButton(
                    onClick = {},
                    text = "Test button",
                    leadingIcon = ToolkitIconCatalog.Add,
                )

                ToolkitTextButton(
                    onClick = { },
                    label = "Test button",
                    leadingIcon = ToolkitIconCatalog.Add,
                    trailingIcon = ToolkitIconCatalog.Close
                )

                ToolkitLinkButton(
                    onClick = { },
                    label = "Link button",
                )

                ToolkitFloatingButton(
                    imageVector = ToolkitIconCatalog.Add,
                    onClick = {}
                )

                DefaultSpaceHeight()

                ToolkitDoubleFloatingButton()

                ToolkitToggleButton(
                    checked = true,
                    onCheckedChange = { },
                    icon = {
                        Icon(
                            imageVector = ToolkitIconCatalog.BookmarkBorder,
                            contentDescription = null,
                        )
                    },
                    checkedIcon = {
                        Icon(
                            imageVector = ToolkitIconCatalog.Bookmark,
                            contentDescription = null,
                        )
                    },
                )

                ToolkitToggleButton(
                    checked = false,
                    onCheckedChange = { },
                    icon = {
                        Icon(
                            imageVector = ToolkitIconCatalog.BookmarkBorder,
                            contentDescription = null,
                        )
                    },
                    checkedIcon = {
                        Icon(
                            imageVector = ToolkitIconCatalog.Bookmark,
                            contentDescription = null,
                        )
                    },
                )

                ToolkitToggleView(
                    expanded = true,
                    onExpandedChange = { },
                    compactText = { Text(text = "Compact view") },
                    expandedText = { Text(text = "Expanded view") },
                )

                ToolkitToggleView(
                    expanded = false,
                    onExpandedChange = { },
                    compactText = { Text(text = "Compact view") },
                    expandedText = { Text(text = "Expanded view") },
                )

                ToolkitFixedButton(label = "Continuar", showLoading = true)
                ToolkitFixedButton(label = "Continuar", showLoading = false)
            }
        }
    }
}