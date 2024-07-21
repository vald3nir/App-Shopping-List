package com.vald3nir.shoppinglist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.compose.components.base.HalfSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitText

@Composable
fun ComponentSection(text: String, background: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background),
        verticalArrangement = Arrangement.Center,
    ) {
        HalfSpaceHeight()
        ToolkitText.Label(
            text = text,
            textColor = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HalfSpaceHeight()
    }
}