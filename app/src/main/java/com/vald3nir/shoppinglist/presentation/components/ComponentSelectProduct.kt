package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.domain.usecases.findProductByName
import com.vald3nir.shoppinglist.domain.usecases.formatProducts
import com.vald3nir.shoppinglist.repository.ProductsRepository
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitAutoCompleteInputComponent
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InputProductNameViewModel @Inject constructor(repository: ProductsRepository) : BaseViewModel() {
    val productsList: Flow<List<ProductDTO>> = repository.getAllProducts().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}

@Composable
fun ComponentSelectProduct(
    inputValue: String,
    useTransparentBackend: Boolean = false,
    onSelected: (ProductDTO) -> Unit,
    colors: ScreenColorSchema,
) {
    val viewModel = hiltViewModel<InputProductNameViewModel>()
    val products by viewModel.productsList.collectAsState(initial = emptyList())
    ToolkitAutoCompleteInputComponent(
        useTransparentBackend = useTransparentBackend,
        inputValue = inputValue,
        suggestionList = products.formatProducts(),
        colors = colors,
        label = "Nome do produto",
        placeholder = "Produto",
        startIcon = ToolkitIcons.ShoppingBasket,
        onSelected = { productName ->
            onSelected(products.findProductByName(productName))
        },
    )
}