package login

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    init {
        // Establece el callback al iniciar el ViewModel
        AuthProviderBridge().setLoginCallback(object : LoginCallback {
            override fun onLoginSuccess(userData: UserData) {
                _state.update { it.copy(isSignInSuccessful = true) }
            }

            override fun onLoginFailure(errorMessage: String) {
                _state.update { it.copy(signInError = errorMessage) }
            }
        })
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