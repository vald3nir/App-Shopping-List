package com.vald3nir.toolkit.auth.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_GOOGLE_SERVER_CLIENT_ID
import com.vald3nir.toolkit.auth.presentation.navigaton.AuthNavHost
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.AuthNavHost(serverClientId = intent.getStringExtra(AUTH_LIB_PARAM_GOOGLE_SERVER_CLIENT_ID) ?: "")
        }
    }
}

fun Context.buildAuthActivityIntent(serverClientId: String): Intent = Intent(this, AuthActivity::class.java).apply {
    putExtra(AUTH_LIB_PARAM_GOOGLE_SERVER_CLIENT_ID, serverClientId)
}