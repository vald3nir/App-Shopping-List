package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vald3nir.shoppinglist.domain.ProfileScreenDTO
import com.vald3nir.shoppinglist.presentation.components.buildTopBar
import com.vald3nir.shoppinglist.presentation.theme.AppTheme
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.templates.ToolkitBaseContent
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

@Composable
internal fun ProfileScreenContent(
    profileData: ProfileScreenDTO? = null,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onChangeThemeBrand: (themeBrand: ThemeBrandEnum) -> Unit = {},
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit = {},
    onChangeDarkThemeConfig: (uIThemeConfigEnum: UIThemeConfigEnum) -> Unit = {},
    onClickLogout: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {

    ToolkitBaseContent(
        snackBarHostState = snackBarHostState,
        topBar = buildTopBar(
            title = "Perfil",
            onBackPressed = onBackPressed,
        ),
        content = {
            // Estado para os RadioButtons
//            val options = listOf("Opção A", "Opção B", "Opção C", "Opção D")
            val options = UIThemeConfigEnum.entries

            val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[0]) }

            Column(modifier = Modifier.padding(ToolkitSpacingMd), horizontalAlignment = Alignment.CenterHorizontally) {

                ProfileHeader(user = profileData?.user, onClickLogout = onClickLogout)
                ToolkitSpaceHeight(ToolkitSpacingXl)

                // --- Box de Radio Buttons em Duas Colunas ---
                Text(
                    text = "Configurações de Perfil",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                ToolkitSpaceHeight(ToolkitSpacingSm)
                Column(modifier = Modifier.fillMaxWidth()) {
                    options.chunked(2).forEach { rowOptions ->
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            rowOptions.forEach { themeConfigEnum ->
                                Row(
                                    Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (themeConfigEnum == selectedOption),
                                        onClick = { onOptionSelected(themeConfigEnum) }
                                    )
                                    Text(
                                        text = themeConfigEnum.name,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical))) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(ToolkitSpacingMd),
                    text = "Versão do App: 1.0.42 (Build 2026)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    AppTheme {
        ToolkitBackground {
            ProfileScreenContent()
        }
    }
}