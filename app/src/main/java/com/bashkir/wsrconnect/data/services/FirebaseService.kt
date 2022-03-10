package com.bashkir.wsrconnect.data.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseService {
    private val auth = Firebase.auth

    fun signIn(email: String, password: String, onCompleteListener: (Task<AuthResult>) -> Unit) =
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener)

    fun signUp(
        name: String,
        email: String,
        password: String,
        onCompleteListener: (Task<AuthResult>) -> Unit
    ) = auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            onCompleteListener(it)
            if (it.isSuccessful)
                updateName(name, it.result.user!!)
        }

    fun signInWithGoogle(
        idToken: String,
        onComplete: (Task<AuthResult>) -> Unit
    ) = GoogleAuthProvider.getCredential(idToken, null).let { credential ->
        auth.signInWithCredential(credential)
            .addOnCompleteListener(onComplete)
    }

    fun updateName(
        name: String,
        user: FirebaseUser,
        onSuccess: () -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    ) = user.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener(onFailure)

    fun updateEmail(
        email: String, user: FirebaseUser,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) = user.updateEmail(email)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener(onFailure)

    fun updatePassword(
        password: String, user: FirebaseUser,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) = user.updatePassword(password)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener(onFailure)

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    fun signOut() = auth.signOut()
}