package login

actual class AuthProviderBridge actual constructor() : AuthProvider {
    var loginCallback: LoginCallback? = null
    override fun signIn() {
        //only For IOS
    }

    override fun signOut() {
     //only For IOS
    }

    override fun getCurrentUser(): UserData? {
      return null
    }

    actual override fun setLoginCallback(callback: LoginCallback?) {
        this.loginCallback = callback
    }
}