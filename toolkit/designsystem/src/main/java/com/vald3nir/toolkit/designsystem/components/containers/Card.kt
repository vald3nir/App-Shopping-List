package com.vald3nir.toolkit.designsystem.components.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.defaultSpace
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun ToolkitCard(modifier: Modifier = Modifier, content: @Composable () -> Unit, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(defaultSpace),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    ) {
        Column {
            content()
        }
    }
}

@ThemePreviews
@Composable
private fun CardPreview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier.size(300.dp)) {
            Column(modifier = Modifier.padding(defaultSpace)) {
                ToolkitCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    content = {
                        ToolkitText(text = "HeadlineLarge", style = ToolkitTextStyle.HeadlineLarge)
                        ToolkitText(text = "HeadlineLarge", style = ToolkitTextStyle.HeadlineLarge)
                        ToolkitText(text = "HeadlineLarge", style = ToolkitTextStyle.HeadlineLarge)
                    }
                )
            }
        }
    }
}