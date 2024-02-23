package login

actual class AuthProviderBridge actual constructor() : AuthProvider {
    override fun signIn() {
        // La implementación real será en Swift
    }

    override fun signOut() {
        // La implementación real será en Swift
    }

    override fun getCurrentUser(): UserData? {
        return null
    }
}