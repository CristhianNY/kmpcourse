package login

import org.koin.core.component.KoinComponent
import kotlin.jvm.JvmStatic


object KotlinBridge : KoinComponent {
    @JvmStatic
    fun getSignInViewModel(): SignInViewModel = getKoin().get()
}