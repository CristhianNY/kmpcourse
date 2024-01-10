import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import di.sharedModule
import navigation.RootComponent
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    val root = remember { RootComponent(DefaultComponentContext(LifecycleRegistry())) }
    App(root)
}

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}
