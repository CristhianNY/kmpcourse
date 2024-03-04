import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinContext
import screens.HomeScreen


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(rootComponent: RootComponent) {
    KoinContext {
        appContent(rootComponent)
    }
}

@Composable
fun appContent(rootComponent: RootComponent) {
    MaterialTheme {

        val childStack by rootComponent.childStack.subscribeAsState()

        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->

            when (val instance = child.instance) {
                is RootComponent.Child.ScreenHome -> HomeScreen(instance.component)
                is RootComponent.Child.ScreenLogin -> LoginScreen(instance.component)
            }
        }
    }
}
