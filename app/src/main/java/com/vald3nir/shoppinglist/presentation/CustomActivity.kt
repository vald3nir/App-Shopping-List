package com.vald3nir.shoppinglist.presentation

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.toolkit.compose.extensions.updateStatusBarColor
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity

abstract class CustomActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (BuildConfig.FLAVOR == "dev" || BuildConfig.DEBUG) {
            updateStatusBarColor(statusBarColor = Color.RED)
        }
    }
}