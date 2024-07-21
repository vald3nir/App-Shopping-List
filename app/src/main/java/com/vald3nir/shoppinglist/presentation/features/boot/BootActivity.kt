package com.vald3nir.shoppinglist.presentation.features.boot

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.presentation.CustomActivity
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_LOGIN_RESPONSE
import com.vald3nir.toolkit.auth.AuthLibLoginResponseType
import com.vald3nir.toolkit.auth.presentation.buildAuthActivityIntent
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class BootActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BootScreenContent()
        }
    }
}

@Composable
private fun BootScreenContent() {
    val context = LocalContext.current
    val viewModel = hiltViewModel<BootViewModel>()

    val activityResultLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val response: String? = result.data?.getStringExtra(AUTH_LIB_PARAM_LOGIN_RESPONSE)
            when (response) {
                AuthLibLoginResponseType.SUCCESS.name -> viewModel.downloadDatabase(context)
                AuthLibLoginResponseType.FAKE_USER.name -> viewModel.useFakeData(context)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkUserLoggedAndDownloadDatabase(
            context = context,
            onRedirectToAuth = {
                val intent = context.buildAuthActivityIntent(serverClientId = BuildConfig.SERVER_CLIENT_ID)
                activityResultLauncher.launch(intent)
            }
        )
    }

    ToolkitBaseLoadingScreen()
}