package com.vald3nir.toolkit.designsystem.components.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.DefaultSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitTextButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun ToolkitDisclaimer(
    imageVector: ImageVector = ToolkitIconCatalog.Warning,
    description: String,
    linkText: String? = null,
    onClickLink: () -> Unit = {}
) {
    val containerColor = MaterialTheme.colorScheme.inverseSurface
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier.padding(ToolkitSpacingMd),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ToolkitIcon(imageVector = imageVector)
                DefaultSpaceWidth()
                ToolkitText(
                    text = description,
                    style = ToolkitTextStyle.LabelMedium
                )
            }
            if (!linkText.isNullOrBlank()) {
                ToolkitSpaceHeight()
                ToolkitTextButton(
                    onClick = onClickLink,
                    label = linkText,
                    trailingIcon = ToolkitIconCatalog.ChevronRight,
                    colors = ButtonDefaults.buttonColors(containerColor = containerColor)
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitDisclaimer(
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    linkText = "Saiba mais"
                )
                ToolkitSpaceHeight()
                ToolkitDisclaimer(
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                )
            }
        }
    }
}