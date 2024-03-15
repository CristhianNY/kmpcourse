package login

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel( authProvider: AuthProvider? = null) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    init {
        authProvider?.setLoginCallback(createLoginCallback())
    }

    fun createLoginCallback(): LoginCallback {

        return object : LoginCallback {
            override fun onLoginSuccess(userData: UserData) {
                _state.update { it.copy(isSignInSuccessful = true) }
                println("Login successful with user: ${userData.userId}")
            }

            override fun onLoginFailure(errorMessage: String) {
                _state.update { it.copy(signInError = errorMessage) }
                println("Login failed with error: $errorMessage")
            }
        }
    }

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}