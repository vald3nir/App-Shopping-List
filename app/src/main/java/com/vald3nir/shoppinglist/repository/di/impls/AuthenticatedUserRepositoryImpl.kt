package com.vald3nir.shoppinglist.repository.di.impls

import com.vald3nir.shoppinglist.repository.usecases.UserLoggedUseCase
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import javax.inject.Inject

internal class AuthenticatedUserRepositoryImpl @Inject constructor(
    private val userLoggedUseCase: UserLoggedUseCase
) : AuthenticatedUserRepository {

    // Authenticated User
    override suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?) = userLoggedUseCase.update(authenticatedUser)

    override fun loadAuthenticatedUser() = userLoggedUseCase.load()

    override suspend fun logout() = userLoggedUseCase.logout()
}