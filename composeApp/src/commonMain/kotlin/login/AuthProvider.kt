package login

interface AuthProvider {
    fun signIn()
    fun signOut()
    fun getCurrentUser(): UserData?
    fun setLoginCallback(callback: LoginCallback?) {}
}

