//package com.vald3nir.shoppinglist.core.ui.thema
//
//import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum2
//import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
//import com.vald3nir.toolkit.themas.domain.AppThemeDTO
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//internal object AppThemaModule {
//
//    @Provides
//    @Singleton
//    fun provideDefaultTheme(): AppThemeDTO {
//        return AppThemeDTO(
//            themeBrand = ThemeBrandEnum2.RED,
//            themeConfigEnum = UIThemeConfigEnum.DARK,
//            useDynamicColor = false,
//        )
//    }
//}