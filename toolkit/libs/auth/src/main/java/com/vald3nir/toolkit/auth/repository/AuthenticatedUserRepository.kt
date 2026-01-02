package com.vald3nir.toolkit.auth.repository

import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import kotlinx.coroutines.flow.Flow

interface AuthenticatedUserRepository {
    suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?)
    fun loadAuthenticatedUser(): Flow<AuthenticatedUserDTO>
    suspend fun logout()

}