package com.vald3nir.toolkit.themas.repository.datasource

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import com.vald3nir.toolkit.themas.domain.AppThemeDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.userPreferencesStore by preferencesDataStore(name = "user_prefs")

object DataStoreKeys {
    val THEME_BRAND = stringPreferencesKey("theme_brand")
    val DARK_THEME = stringPreferencesKey("dark_theme")
    val USE_DYNAMIC_COLOR = booleanPreferencesKey("use_dynamic_color")
}

class ThemaDataSource(private val context: Context) {

    val appThemaFlow: Flow<AppThemeDTO> = context.userPreferencesStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else throw exception
    }.map { prefs ->
        AppThemeDTO(
            themeBrand = ThemeBrandEnum2.fromKey(prefs[DataStoreKeys.THEME_BRAND]),
            themeConfigEnum = UIThemeConfigEnum.fromKey(prefs[DataStoreKeys.DARK_THEME]),
            useDynamicColor = prefs[DataStoreKeys.USE_DYNAMIC_COLOR] ?: false,
        )
    }

    suspend fun setThemeBrand(themeBrand: ThemeBrandEnum2) {
        context.userPreferencesStore.edit { prefs ->
            prefs[DataStoreKeys.THEME_BRAND] = themeBrand.key
        }
    }

    suspend fun setDynamicColorPreference(enabled: Boolean) {
        context.userPreferencesStore.edit { prefs ->
            prefs[DataStoreKeys.USE_DYNAMIC_COLOR] = enabled
        }
    }

    suspend fun setDarkThemeConfig(config: UIThemeConfigEnum) {
        context.userPreferencesStore.edit { prefs ->
            prefs[DataStoreKeys.DARK_THEME] = config.key
        }
    }
}