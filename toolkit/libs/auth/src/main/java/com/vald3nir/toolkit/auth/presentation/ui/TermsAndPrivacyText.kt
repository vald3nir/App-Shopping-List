package com.vald3nir.toolkit.auth.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
internal fun TermsAndPrivacyText(
    modifier: Modifier = Modifier,
    onClickTerms: () -> Unit = {},
    onClickPrivacyPolicy: () -> Unit = {},
) {
    val annotatedText = buildAnnotatedString {
        append(stringResource(R.string.auth_terms_politics_description))
        append(" ")
        withLink(
            LinkAnnotation.Clickable(
                tag = "TERMS",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = { onClickTerms() }
            )
        ) {
            append(stringResource(R.string.auth_terms_and_conditions))
        }
        append(" e ")
        withLink(
            LinkAnnotation.Clickable(
                tag = "PRIVACY",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = { onClickPrivacyPolicy() }
            )
        ) {
            append(stringResource(R.string.auth_privacy_policy))
        }
    }
    Text(
        text = annotatedText,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitTheme {
        ToolkitBackground(
            modifier = Modifier.size(width = 350.dp, height = 50.dp),
            content = {
                TermsAndPrivacyText()
            }
        )
    }
}