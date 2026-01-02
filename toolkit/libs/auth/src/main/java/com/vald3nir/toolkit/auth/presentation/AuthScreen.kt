package com.vald3nir.toolkit.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.toolkit.auth.presentation.ui.Body
import com.vald3nir.toolkit.auth.presentation.ui.Header
import com.vald3nir.toolkit.auth.presentation.ui.TermsAndPrivacyText
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.openLinkURL
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
internal fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    appPrivacyPolicyURL: String,
    appTermsUseLink: String,
    webGoogleClientID: String,
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    var showLoading: Boolean by remember { mutableStateOf(false) }
    val hasInternetConnection by viewModel.hasInternetConnection.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is BaseUiState.LoadingState -> showLoading = (uiState as BaseUiState.LoadingState).show
        else -> Unit
    }

    AuthScreenContent(
        showLoading = showLoading,
        hasInternetConnection = hasInternetConnection,
        snackBarHostState = snackBarHostState,
        onClickLogin = {
            viewModel.signInWithGoogle(context, webGoogleClientID)
        },
        onClickTerms = {
            context.openLinkURL(url = appTermsUseLink)
        },
        onClickPrivacyPolicy = {
            context.openLinkURL(url = appPrivacyPolicyURL)
        },
    )
}

@Composable
internal fun AuthScreenContent(
    hasInternetConnection: Boolean = true,
    showLoading: Boolean = false,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onClickLogin: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickPrivacyPolicy: () -> Unit = {},
) {
    ToolkitScaffold(snackBarHostState = snackBarHostState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ToolkitSpacingMd)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Header()
            Body(
                modifier = Modifier.align(Alignment.Center),
                hasInternetConnection = hasInternetConnection,
                showLoading = showLoading,
                onClickLogin = onClickLogin
            )
            TermsAndPrivacyText(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClickTerms = onClickTerms,
                onClickPrivacyPolicy = onClickPrivacyPolicy
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewContent() {
    ToolkitTheme {
        ToolkitBackground {
            AuthScreenContent()
        }
    }
}