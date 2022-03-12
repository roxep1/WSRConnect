package com.bashkir.wsrconnect

import android.content.Context
import com.airbnb.mvrx.*
import com.bashkir.wsrconnect.data.services.FirebaseService
import com.bashkir.wsrconnect.utils.isValid
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class ConnectViewModel(
    initialState: ConnectState,
    private val context: Context,
    private val firebaseService: FirebaseService
) :
    MavericksViewModel<ConnectState>(initialState) {

    init {
        firebaseService.getCurrentUser()?.let { user ->
            setUser(user)
        }
    }

    fun signOut() {
        firebaseService.signOut()
        GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
        setState { copy(user = Uninitialized) }
    }

    fun updateName(name: String, onFailure: (Exception) -> Unit = {}) = withState { state ->
        firebaseService.updateName(
            name,
            state.user()!!,
            onSuccess = { setUser(firebaseService.getCurrentUser()!!) },
            onFailure = onFailure
        )
    }

    fun updatePassword(password: String, onFailure: (Exception) -> Unit = {}) = withState { state ->
        firebaseService.updatePassword(
            password,
            state.user()!!,
            onSuccess = { setUser(firebaseService.getCurrentUser()!!) },
            onFailure = onFailure
        )
    }

    fun updateEmail(email: String, onFailure: (Exception) -> Unit = {}) = withState { state ->
        firebaseService.updateEmail(
            email,
            state.user()!!,
            onSuccess = { setUser(firebaseService.getCurrentUser()!!) },
            onFailure = onFailure
        )
    }

    fun signIn(email: String, password: String) {
        if (isValid(email, password))
            firebaseService.signIn(email, password) { task ->
                when {
                    task.isSuccessful -> setUser(task.result.user!!)
                    task.isCanceled -> setFail(task.exception)
                }
            }
        else setFail("Данные введены неверно")
    }

    private fun signInWithIdToken(idToken: String) =
        firebaseService.signInWithGoogle(idToken) { task ->
            when {
                task.isSuccessful -> setUser(task.result.user!!)
                task.isCanceled -> setFail(task.exception)
            }
        }

    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        if (isValid(email, name, password, confirmPassword) && password == confirmPassword)
            firebaseService.signUp(name, email, password) { task ->
                when {
                    task.isSuccessful -> setUser(task.result.user!!)
                    task.isCanceled -> setFail(task.exception)
                }
            }
        else setFail("Данные введены неверно")
    }

    fun onSignInResult(task: Task<GoogleSignInAccount>?) =
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account != null)
                signInWithIdToken(account.idToken!!)
            else setFail("Неудачная авторизация")
        } catch (e: ApiException) {
            setFail(e)
        }

    private fun setUser(user: FirebaseUser) = setState { copy(user = Success(user)) }


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


data class ConnectState(
    val user: Async<FirebaseUser> = Uninitialized
) : MavericksState