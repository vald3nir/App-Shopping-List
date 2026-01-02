package com.vald3nir.toolkit.designsystem.components.menus

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceHeight
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun ToolkitHorizontalSelector(
    modifier: Modifier = Modifier,
    title: String,
    options: List<String>,
    optionUnselectedTextColor: Color = Color.Black,
    optionSelectedTextColor: Color = Color.White,
    optionSelectedColor: Color = Color.Blue,
    optionUnselectedColor: Color = Color.LightGray,
    response: (String) -> Unit = {}
) {
    var selectedOption by remember { mutableStateOf(options.first()) }
    val limitItemsNoScroll = 3
    Column(modifier = modifier) {
        ToolkitText(text = title, style = ToolkitTextStyle.TitleSmall)
        DefaultSpaceHeight()
        Row(
            modifier = if (options.size > limitItemsNoScroll) Modifier.horizontalScroll(rememberScrollState()) else Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val buttonsModifier: Modifier = if (options.size > limitItemsNoScroll) Modifier else Modifier.weight(1f)
            options.forEach { option ->
                Button(
                    onClick = {
                        selectedOption = option
                        response(option)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) optionSelectedColor else optionUnselectedColor
                    ),
                    modifier = buttonsModifier
                ) {
                    Text(text = option, color = if (selectedOption == option) optionSelectedTextColor else optionUnselectedTextColor)
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                ToolkitHorizontalSelector(
                    title = "Titulo",
                    options = listOf("Opção 1", "Opção 2", "Opção 3")
                )
                DefaultSpaceHeight()
                ToolkitHorizontalSelector(
                    title = "Titulo 2",
                    options = listOf("Opção 1", "Opção 2", "Opção 3", "Opção 4", "Opção 5")
                )
            }
        }
    }
}