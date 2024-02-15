package navigation

import com.arkivanov.decompose.ComponentContext
import login.UserData

class ScreenHomeComponent(
    val userData: UserData?,
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val singOut: () -> Unit,

) : ComponentContext by componentContext {

    fun goBack() {
        onBack.invoke()
    }

    fun onEvent(event: ScreenHomeEvents) {
        when (event) {
            ScreenHomeEvents.SingOut -> singOut.invoke()
        }
    }
}