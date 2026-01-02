package com.vald3nir.toolkit.core.baseclasses

import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {

    protected fun <T> configThemeListener(uiState: StateFlow<T>) {}

}