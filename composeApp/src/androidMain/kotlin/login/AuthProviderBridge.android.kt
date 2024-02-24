package login

actual class AuthProviderBridge actual constructor() : AuthProvider {
    private var _loginCallback: LoginCallback? = null

    actual override fun setLoginCallback(callback: LoginCallback?) {
        this._loginCallback = callback
    }

    override fun signIn() {
        // Implementación específica para iOS
    }

    override fun signOut() {
        // Implementación específica para iOS
    }

    override fun getCurrentUser(): UserData? {
        // Implementación específica para iOS o común si aplicable
        return null
    }
}