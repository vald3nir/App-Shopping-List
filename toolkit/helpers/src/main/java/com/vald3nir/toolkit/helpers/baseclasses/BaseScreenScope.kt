package com.vald3nir.toolkit.helpers.baseclasses

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.vald3nir.toolkit.helpers.navigation.UiDestination

open class BaseScreenScope(
    private val viewModel: BaseViewModel? = null,
    private val navController: NavController? = null
) {

    @Composable
    fun CollectUiState(
        onLoading: (Boolean) -> Unit = {},
        onCallbackScreen: (Any?) -> Unit = {},
        onShowMessage: (String) -> Unit = {},
    ) {
        LaunchedEffect(Unit) {
            viewModel?.uiState?.collect { uiEvent ->
                when (uiEvent) {
                    is BaseScreenState.Loading -> onLoading(uiEvent.show)
                    is BaseScreenState.CallbackScreen -> onCallbackScreen(uiEvent.response)
                    is BaseScreenState.ShowMessage -> uiEvent.message?.let { onShowMessage(it) }
                }
            }
        }
    }

    @Composable
    fun NavigationObserver() {
        viewModel?.navigationEvent?.collectAsState(initial = null)?.value?.let { navigationValue ->
            NavigatorTo(navigationValue)
        }
    }

    @Composable
    private fun NavigatorTo(destination: UiDestination) {
        LaunchedEffect(destination) {
            navController?.navigate(destination)
        }
    }

    fun onBackPressed(message: String? = null) {
        message?.let { navController?.previousBackStackEntry?.savedStateHandle?.set("PARAM", it) }
        navController?.popBackStack()
    }

    fun getBackPressedMessage() = navController?.currentBackStackEntry?.savedStateHandle?.get<String?>("PARAM")

    @Composable
    fun SnackbarHostState.ShowBackPressedMessage() {
        getBackPressedMessage()?.let { message ->
            LaunchedEffect(Unit) {
                showSnackbar(message)
            }
        }
    }
}