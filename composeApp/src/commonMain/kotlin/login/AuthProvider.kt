package login

interface AuthProvider {
    fun signIn()
    fun signOut()
    fun getCurrentUser(): UserData?
}

