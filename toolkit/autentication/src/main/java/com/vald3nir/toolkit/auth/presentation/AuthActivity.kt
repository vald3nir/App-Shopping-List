package com.vald3nir.toolkit.auth.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_GOOGLE_SERVER_CLIENT_ID
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_IMAGE_LOGO
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_IMAGE_LOGO_INVALID
import com.vald3nir.toolkit.auth.presentation.navigaton.AuthNavHost
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.AuthNavHost(
                serverClientId = intent.getStringExtra(AUTH_LIB_PARAM_GOOGLE_SERVER_CLIENT_ID).orEmpty(),
                imageLogo = intent.getIntExtra(AUTH_LIB_PARAM_IMAGE_LOGO, AUTH_LIB_PARAM_IMAGE_LOGO_INVALID)
            )
        }
    }
}

fun Context.buildAuthActivityIntent(serverClientId: String, imageLogo: Int?): Intent = Intent(this, AuthActivity::class.java).apply {
    putExtra(AUTH_LIB_PARAM_GOOGLE_SERVER_CLIENT_ID, serverClientId)
    putExtra(AUTH_LIB_PARAM_IMAGE_LOGO, imageLogo)
}