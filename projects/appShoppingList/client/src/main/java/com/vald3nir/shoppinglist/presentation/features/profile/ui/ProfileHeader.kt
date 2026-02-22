package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.core.ui.thema.AppTheme
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconAvatar
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun ProfileHeader(user: AuthenticatedUserDTO? = null) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        ToolkitIconAvatar(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            userImageUrl = user?.photoUrl
        )
        ToolkitSpaceHeight(ToolkitSpacingSm)

        ToolkitText(text = user?.name ?: stringResource(R.string.profile_screen_user), style = ToolkitTextStyle.TitleLarge)
        user?.email?.let { ToolkitText(text = it, style = ToolkitTextStyle.TitleSmall) }

        ToolkitSpaceHeight()
        HorizontalDivider()
        ToolkitSpaceHeight()
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    AppTheme {
        ToolkitBackground {
            ProfileHeader(
                AuthenticatedUserDTO(
                    name = "Valdenir Severino",
                    email = "vald3nir@gmail.com"
                )
            )
        }
    }
}