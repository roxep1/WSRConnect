package com.bashkir.wsrconnect

import android.content.Context
import com.airbnb.mvrx.*
import com.bashkir.wsrconnect.utils.isValid
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class ConnectViewModel(initialState: ConnectState, private val context: Context) :
    MavericksViewModel<ConnectState>(initialState) {

    private val auth = Firebase.auth

    init {
//        auth.currentUser?.let { user ->
//            setState { copy(user = Success(user)) }
//        }
    }

    fun signIn(email: String, password: String) {
        if (isValid(email, password))
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> setState { copy(user = Success(auth.currentUser!!)) }
                    task.isCanceled -> setFail(task.exception)
                }
            }
        else setFail("Данные введены неверно")
    }

    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        if (isValid(email, name, password, confirmPassword) && password == confirmPassword)
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> signIn(email, password)
                    task.isCanceled -> setFail(task.exception)
                }
            }
        else setFail("Данные введены неверно")
    }

    fun signInWithGoogle() =
        GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.web_client_id))
            .requestEmail()
            .build().let { gso ->
                GoogleSignIn.getClient(context, gso).silentSignIn().run {
                    addOnSuccessListener {
                        firebaseSignInWithIdToken(it.idToken!!)
                    }
                }
            }

    private fun firebaseSignInWithIdToken(idToken: String) =
        GoogleAuthProvider.getCredential(idToken, null).let { credential ->
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    when {
                        task.isSuccessful -> setState { copy(user = Success(auth.currentUser!!)) }
                        task.isCanceled -> setFail(task.exception)
                    }
                }
        }


    private fun setFail(exception: Exception?) =
        setFail(exception?.localizedMessage)

    private fun setFail(message: String?) =
        setState { copy(user = Fail(Throwable(message))) }

    companion object : MavericksViewModelFactory<ConnectViewModel, ConnectState>, KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ConnectState
        ): ConnectViewModel = get { parametersOf(state) }
    }
}

data class ConnectState(val user: Async<FirebaseUser> = Uninitialized) : MavericksState