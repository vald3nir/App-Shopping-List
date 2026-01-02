package com.vald3nir.toolkit.auth.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import kotlinx.coroutines.tasks.await

object FirebaseAuthenticator {

    fun disconnect() {
        Firebase.auth.signOut()
    }

    fun getFirebaseUser() = Firebase.auth.currentUser?.let {
        AuthenticatedUserDTO(
            uuid = it.uid,
            name = it.displayName,
            email = it.email,
            photoUrl = it.photoUrl?.toString()
        )
    }

    fun isUserLogged(): Boolean = getFirebaseUser() != null

    suspend fun authenticate(googleIdToken: String): AuthenticatedUserDTO? {
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
        Firebase.auth.signInWithCredential(credential).await()
        return getFirebaseUser()
    }
}