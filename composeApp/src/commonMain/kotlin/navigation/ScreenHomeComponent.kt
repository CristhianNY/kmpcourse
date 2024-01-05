package navigation

import com.arkivanov.decompose.ComponentContext

class ScreenHomeComponent(
    val text: String,
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext {

    fun goBack() {
        onBack.invoke()
    }
}