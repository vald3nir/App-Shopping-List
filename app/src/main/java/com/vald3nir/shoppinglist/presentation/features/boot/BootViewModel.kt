package com.vald3nir.shoppinglist.presentation.features.boot

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.shoppinglist.presentation.features.shoppingList.startShoppingListActivity
import com.vald3nir.shoppinglist.repository.ImportDataRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BootViewModel @Inject constructor(private val repository: ImportDataRepository) : BaseViewModel() {

    fun checkUserLoggedAndDownloadDatabase(context: Context, onRedirectToAuth: () -> Unit) {
        viewModelScope.launch {
            kotlin.runCatching {
                if (!FirebaseAuthenticator.isUserLogged()) {
                    onRedirectToAuth()
                    return@launch
                }
                downloadDatabase(context)
            }
        }
    }

    fun downloadDatabase(context: Context) {
        viewModelScope.launch {
            repository.importProductsFromServer(context)
            repository.importShoppingListFromServer()
            context.startShoppingListActivity()
        }
    }

    fun useFakeData(context: Context) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.importProductsFromLocal(context)
                repository.importShoppingListFromLocal()
                context.startShoppingListActivity()
            }
        }
    }
}