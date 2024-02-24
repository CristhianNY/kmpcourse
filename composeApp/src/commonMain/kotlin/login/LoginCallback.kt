package login

interface LoginCallback {
    fun onLoginSuccess(userData: UserData)
    fun onLoginFailure(errorMessage: String)
}