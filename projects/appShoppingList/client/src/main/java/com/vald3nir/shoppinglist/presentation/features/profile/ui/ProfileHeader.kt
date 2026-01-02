package com.vald3nir.shoppinglist.presentation.features.profile.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.buttons.AlertButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconAvatar
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
internal fun ProfileHeader(
    user: AuthenticatedUserDTO? = null,
    onClickLogout: () -> Unit = {}
) {
    ToolkitIconAvatar(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape), userImageUrl = user?.photoUrl
    )
    ToolkitSpaceHeight()
    ToolkitText(text = user?.name ?: stringResource(R.string.profile_screen_user), style = ToolkitTextStyle.TitleLarge)
    user?.email?.let { ToolkitText(text = it, style = ToolkitTextStyle.TitleSmall) }
    ToolkitSpaceHeight()
    AlertButton(onClick = onClickLogout, text = stringResource(R.string.profile_screen_btn_logout))
}