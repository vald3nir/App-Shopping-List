package com.vald3nir.shoppinglist.core.repository

import com.vald3nir.shoppinglist.core.repository.database.dao.UserDao
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import com.vald3nir.toolkit.core.baseclasses.UserAuthenticationException
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AuthenticatedUserRepositoryImpl @Inject constructor(private val userDao: UserDao) : AuthenticatedUserRepository {

    override suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?) {
        if (authenticatedUser == null) throw UserAuthenticationException()
        userDao.insertOrUpdateUser(
            userDao.getCurrentUser().copy(
                name = authenticatedUser.name,
                email = authenticatedUser.email,
                photoUrl = authenticatedUser.photoUrl
            )
        )
    }

    override fun loadAuthenticatedUser() = userDao.loadCurrentUserFlow().map { entity ->
        AuthenticatedUserDTO(
            id = entity?.id,
            name = entity?.name,
            email = entity?.email,
            photoUrl = entity?.photoUrl,
        )
    }

    override suspend fun logout() {
        FirebaseAuthenticator.disconnect()
        userDao.clearUser()
    }
}