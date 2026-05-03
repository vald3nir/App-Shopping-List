package com.vald3nir.shoppinglist.repository.di.impls

import com.vald3nir.shoppinglist.repository.usecases.UserLoggedUseCase
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import io.github.jan.supabase.SupabaseClient
import java.util.UUID
import javax.inject.Inject

internal class AuthenticatedUserRepositoryImpl @Inject constructor(
    private val userLoggedUseCase: UserLoggedUseCase,
    private val supabaseClient: SupabaseClient,
) : AuthenticatedUserRepository {

    // Authenticated User
    override suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?) = userLoggedUseCase.update(authenticatedUser)

    override fun loadAuthenticatedUser() = userLoggedUseCase.load()

    override suspend fun logout() = userLoggedUseCase.logout()

    override suspend fun onAuthenticateWithGoogle(googleIdToken: String, uuid: UUID) {
//        supabaseClient.auth.signInWith(IDToken) { // todo valdenir remover dependencia do supabase
//            idToken = googleIdToken
//            provider = Google
//            nonce = uuid.toString()
//        }
    }
}