package com.vald3nir.shoppinglist.presentation.features.appscreen

import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AppScreenViewModel @Inject constructor(
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters)