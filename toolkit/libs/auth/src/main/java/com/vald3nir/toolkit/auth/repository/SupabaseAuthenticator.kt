package com.vald3nir.toolkit.auth.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import java.util.UUID

suspend fun SupabaseClient.authenticate(googleIdToken: String, uuid: UUID) {
    auth.signInWith(IDToken) {
        idToken = googleIdToken
        provider = Google
        nonce = uuid.toString()
    }
}