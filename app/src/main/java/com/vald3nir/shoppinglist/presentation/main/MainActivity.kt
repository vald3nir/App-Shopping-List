package com.vald3nir.shoppinglist.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.shoppinglist.presentation.features.app.AppScreen
import com.vald3nir.toolkit.core.baseclasses.BaseActivity
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolkitTheme(
                themeBrandEnum = themeSettings.themaBrandEnum,
                darkTheme = themeSettings.darkTheme,
                disableDynamicTheming = themeSettings.disableDynamicTheming,
            ) {
                AppScreen()
            }
        }
    }
}