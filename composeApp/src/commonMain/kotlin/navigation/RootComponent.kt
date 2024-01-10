package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable

class RootComponent(componentContext: ComponentContext) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.ScreeLogin,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.ScreeLogin -> Child.ScreenLogin(
                ScreenLoginComponent(
                    context,
                    onNavigationToHome = { text ->
                        navigation.pushNew(Configuration.ScreenHome(text))

                    })
            )

            is Configuration.ScreenHome -> Child.ScreenHome(
                ScreenHomeComponent(
                    config.text,
                    context,
                    onBack = {
                        navigation.pop()
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
        data object ScreeLogin : Configuration()

        @Serializable
        data class ScreenHome(val text: String) : Configuration()
    }
}