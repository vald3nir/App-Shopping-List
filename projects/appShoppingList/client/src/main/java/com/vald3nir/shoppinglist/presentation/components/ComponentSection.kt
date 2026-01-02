package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun ComponentSection(text: String) {
    val isDarkTheme: Boolean = isSystemInDarkTheme()
    Column(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(if (isDarkTheme) Color.LightGray else Color.DarkGray),
        verticalArrangement = Arrangement.Center,
    ) {
        ToolkitText(
            text = text,
            style = ToolkitTextStyle.LabelMedium,
            textColor = if (isDarkTheme) Color.Black else Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    AppTheme {
        ToolkitBackground(modifier = Modifier) {
            Column {
                ComponentSection("Exemplo 1")
                DefaultSpaceHeight()
                ComponentSection("Exemplo 2")
            }
        }
    }
}