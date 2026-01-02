package com.vald3nir.toolkit.designsystem.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(modifier = Modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                ToolkitInputText(
                    leftIcon = ToolkitIconCatalog.Person,
                    inputValue = "Texto pré-preenchido",
                    label = "exemplo de label",
                    placeholder = "Digite seu nome aqui",
                    errorValue = "mensagem de erro"
                )

                ToolkitAutoCompleteInput(
                    label = "Fruta",
                    placeholder = "Digite o nome da fruta",
                    inputValue = "Banana",
                    suggestionList = emptyList()
                )

                ToolkitSearchFiled(
                    label = "Fruta",
                    placeholder = "Digite o nome da fruta",
                    searchQuery = "Banana",
                )

                ToolkitSearchFiled(
                    label = "Digite o nome da fruta",
                    searchQuery = "",
                )

                ToolkitInputInteger(
                    inputValue = 100,
                    label = "Quantidade de itens",
                    placeholder = "Ex: 1",
                    leftIcon = ToolkitIconCatalog.Pin,
                    onValueChange = { },
                )

                ToolkitInputMonetary(
                    inputValue = 100.0,
                    label = "Quantidade de itens",
                    placeholder = "Ex: 1",
                    leftIcon = ToolkitIconCatalog.AttachMoney,
                    onValueChange = { },
                )

                ToolkitInputPassword(
                    inputValue = "Minha senha",
                    label = "Confirme a senha",
                    placeholder = "digite sua senha",
                    onValueChange = { },
                )
            }
        }
    }
}