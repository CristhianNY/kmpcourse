package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import kotlinx.serialization.Serializable
import login.AuthProvider
import login.UserData

class RootComponent(
    componentContext: ComponentContext,
    private val authProvider: AuthProvider?
) : ComponentContext by componentContext {

    private val navigation: StackNavigation<Configuration> = StackNavigation<Configuration>()

    private val initialConfiguration: Configuration get() =
        authProvider?.getCurrentUser()?.let { Configuration.ScreenHome(it) }
            ?: Configuration.ScreenLogin

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration =  initialConfiguration,
        childFactory = ::createChild
    )

    private fun resetToLoginScreen() {
        navigation.replaceAll(Configuration.ScreenLogin)
    }


    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.ScreenLogin -> Child.ScreenLogin(
                ScreenLoginComponent(
                    context,
                    onNavigationToHome = { text ->
                        authProvider?.signIn()
                    }, onLoginSuccess = {
                        navigation.pushNew(Configuration.ScreenHome(authProvider?.getCurrentUser()))
                    })
            )

            is Configuration.ScreenHome -> Child.ScreenHome(
                ScreenHomeComponent(
                    config.userData,
                    context,
                    onBack = {
                        navigation.pop()
                    },
                    singOut = {
                        authProvider?.signOut()
                        resetToLoginScreen()
                    })
            )
        }
    }


    sealed class Child {
        data class ScreenLogin(val component: ScreenLoginComponent) : Child()
        data class ScreenHome(val component: ScreenHomeComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object ScreenLogin : Configuration()

        @Serializable
        data class ScreenHome(val userData: UserData?) : Configuration()
    }
}