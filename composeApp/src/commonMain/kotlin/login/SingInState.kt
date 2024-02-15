package login

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isSignOutSuccess: Boolean = false
)
