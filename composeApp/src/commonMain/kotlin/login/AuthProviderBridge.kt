package login

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class AuthProviderBridge() : AuthProvider {
    override fun setLoginCallback(callback: LoginCallback?)
}