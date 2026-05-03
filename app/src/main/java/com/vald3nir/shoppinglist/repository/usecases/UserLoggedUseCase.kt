package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.repository.database.dao.UserDao
import com.vald3nir.shoppinglist.repository.database.entities.UserEntity
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import com.vald3nir.toolkit.core.baseclasses.UserAuthenticationException
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserLoggedUseCase @Inject constructor(
    private val userDao: UserDao,
) {

    suspend fun update(authenticatedUser: AuthenticatedUserDTO?) {
        if (authenticatedUser == null) throw UserAuthenticationException()
        userDao.insertOrUpdateUser(
            UserEntity(
                name = authenticatedUser.name,
                email = authenticatedUser.email,
                photoUrl = authenticatedUser.photoUrl
            )
        )
    }

    fun load() = userDao.loadCurrentUserFlow().map { entity ->
        AuthenticatedUserDTO(
            id = entity?.id,
            name = entity?.name,
            email = entity?.email,
            photoUrl = entity?.photoUrl,
        )
    }

    suspend fun logout() {
        FirebaseAuthenticator.disconnect()
        userDao.clearUser()
    }
}