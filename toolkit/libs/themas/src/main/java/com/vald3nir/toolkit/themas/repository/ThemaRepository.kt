package com.vald3nir.toolkit.themas.repository

import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import com.vald3nir.toolkit.themas.domain.AppThemeDTO
import com.vald3nir.toolkit.themas.repository.datasource.ThemaDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ThemaRepository {
    val appThemaFlow: Flow<AppThemeDTO>
    suspend fun setThemeBrand(themeBrand: ThemeBrandEnum2?)
    suspend fun setDarkThemeConfig(themeConfigEnum: UIThemeConfigEnum?)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}

internal class ThemaRepositoryImpl @Inject constructor(private val themaDataSource: ThemaDataSource) : ThemaRepository {

    override val appThemaFlow: Flow<AppThemeDTO> = themaDataSource.appThemaFlow

    override suspend fun setThemeBrand(themeBrand: ThemeBrandEnum2?) {
        themeBrand?.let {
            themaDataSource.setThemeBrand(it)
        }
    }

    override suspend fun setDarkThemeConfig(themeConfigEnum: UIThemeConfigEnum?) {
        themeConfigEnum?.let {
            themaDataSource.setDarkThemeConfig(it)
        }
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        themaDataSource.setDynamicColorPreference(useDynamicColor)
    }
}