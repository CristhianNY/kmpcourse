package navigation

sealed interface ScreenLoginEvent {
    data object ClickButtonLogin : ScreenLoginEvent
    data class UpdateText(val text: String) : ScreenLoginEvent
    data object GoToHome: ScreenLoginEvent
}