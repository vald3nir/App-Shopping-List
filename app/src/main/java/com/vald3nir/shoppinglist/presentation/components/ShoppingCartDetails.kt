package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.core.utils.extensions.toMoney
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun ShoppingCartDetails(countAdded: Int, countNotAdded: Int, totalPrice: Double) {
    Column {
        CartRow(label = stringResource(R.string.list_details_cart_on_size), value = "$countAdded de ${countAdded + countNotAdded}")
        CartRow(label = stringResource(R.string.list_details_cart_total), value = totalPrice.toMoney())
        ToolkitDivider(modifier = Modifier.padding(start = ToolkitSpacingMd, top = ToolkitSpacingSm, end = ToolkitSpacingMd))
    }
}

@Composable
private fun CartRow(label: String, value: String) {
    val style = ToolkitTextStyle.TitleSmall
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = ToolkitSpacingMd, top = ToolkitSpacingSm, end = ToolkitSpacingMd),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ToolkitText(text = label, style = style)
        ToolkitText(text = value, style = style)
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 100.dp)) {
        ShoppingCartDetails(countAdded = 3, countNotAdded = 2, totalPrice = 150.0)
    }
}
