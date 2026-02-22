package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.ProfileScreenDTO
import com.vald3nir.toolkit.core.utils.extensions.orFalse
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import com.vald3nir.toolkit.designsystem.theme.supportsDynamicTheming

@Composable
internal fun ColumnScope.ProfileThema(
    settingsUiState: ProfileScreenDTO,
    supportDynamicColor: Boolean = supportsDynamicTheming(),
    onChangeThemeBrand: (themeBrand: ThemeBrandEnum) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (uIThemeConfigEnum: UIThemeConfigEnum) -> Unit,
) {
    SettingsPanel(
        settings = settingsUiState,
        supportDynamicColor = supportDynamicColor,
        onChangeThemeBrand = onChangeThemeBrand,
        onChangeDynamicColorPreference = onChangeDynamicColorPreference,
        onChangeDarkThemeConfig = onChangeDarkThemeConfig,
    )
}

@Composable
private fun ColumnScope.SettingsPanel(
    settings: ProfileScreenDTO,
    supportDynamicColor: Boolean,
    onChangeThemeBrand: (themeBrand: ThemeBrandEnum) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (uIThemeConfigEnum: UIThemeConfigEnum) -> Unit,
) {
    SettingsDialogSectionTitle(text = stringResource(R.string.feature_settings_theme))
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = stringResource(R.string.feature_settings_brand_default),
            selected = settings.brand == ThemeBrandEnum.DEFAULT,
            onClick = { onChangeThemeBrand(ThemeBrandEnum.DEFAULT) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(R.string.feature_settings_brand_android),
            selected = settings.brand == ThemeBrandEnum.ANDROID,
            onClick = { onChangeThemeBrand(ThemeBrandEnum.ANDROID) },
        )
    }
    AnimatedVisibility(visible = settings.brand == ThemeBrandEnum.DEFAULT && supportDynamicColor) {
        Column {
            SettingsDialogSectionTitle(text = stringResource(R.string.feature_settings_dynamic_color_preference))
            Column(Modifier.selectableGroup()) {
                SettingsDialogThemeChooserRow(
                    text = stringResource(R.string.feature_settings_dynamic_color_yes),
                    selected = settings.useDynamicColor.orFalse(),
                    onClick = { onChangeDynamicColorPreference(true) },
                )
                SettingsDialogThemeChooserRow(
                    text = stringResource(R.string.feature_settings_dynamic_color_no),
                    selected = !settings.useDynamicColor.orFalse(),
                    onClick = { onChangeDynamicColorPreference(false) },
                )
            }
        }
    }
    SettingsDialogSectionTitle(text = stringResource(R.string.feature_settings_dark_mode_preference))
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = stringResource(R.string.feature_settings_dark_mode_config_system_default),
            selected = settings.uIThemeConfigEnum == UIThemeConfigEnum.FOLLOW_SYSTEM,
            onClick = { onChangeDarkThemeConfig(UIThemeConfigEnum.FOLLOW_SYSTEM) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(R.string.feature_settings_dark_mode_config_light),
            selected = settings.uIThemeConfigEnum == UIThemeConfigEnum.LIGHT,
            onClick = { onChangeDarkThemeConfig(UIThemeConfigEnum.LIGHT) },
        )
        SettingsDialogThemeChooserRow(
            text = stringResource(R.string.feature_settings_dark_mode_config_dark),
            selected = settings.uIThemeConfigEnum == UIThemeConfigEnum.DARK,
            onClick = { onChangeDarkThemeConfig(UIThemeConfigEnum.DARK) },
        )
    }
}

@Composable
private fun SettingsDialogSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )
}

@Composable
fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}